package mchediek.wallet_service.bdd.stepdefs;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.Ampak;
import mchediek.wallet_service.application.WalletManagementService;
import mchediek.wallet_service.domain.Amount;
import mchediek.wallet_service.domain.TransactionManagementService;
import mchediek.wallet_service.domain.Wallet;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class WalletSteps {

    @Autowired
    private WalletManagementService walletService;

    @Autowired
    private TransactionManagementService transactionService;

    @Autowired
    private TestContext testContext;


    @Given("a user {string} does not yet have a wallet")
    public void aUserDoesNotYetHaveAWallet(String userId) {
        Wallet existingWallet = walletService.findWalletByUserId(UUID.fromString(userId));
        Assertions.assertNull(existingWallet,
                () -> "Expected user " + userId + " not to have a wallet, but found one.");
    }

    @Given("a user {string} has an existing wallet with a balance of {double}")
    public void aUserHasAnExistingWalletWithABalanceOf(String userId, double balance) {
        Amount balanceAmount = new Amount(balance);
        Wallet wallet = walletService.createOrUpdateWallet(UUID.fromString(userId), balanceAmount);
        testContext.setWalletIdForUser(userId, wallet.getId().toString());
    }

    @Given("the user {string} wallet's balance was {double} at {string}")
    public void theWalletSBalanceWasAt(String userId, double balance, String dateTime) {
        LocalDateTime timestamp = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String walletId = testContext.getWalletIdForUser(userId);
        Amount balanceAt = new Amount(balance);
        transactionService.recordBalance(UUID.fromString(walletId), balanceAt, timestamp);
    }

    @When("the user {string} requests a new wallet")
    public void theUserRequestsANewWallet(String userId) {

        Wallet wallet = walletService.createOrUpdateWallet(UUID.fromString(userId), new Amount());
        testContext.setWalletIdForUser(userId, wallet.getId().toString());
    }

    @When("the user {string} checks the current balance")
    public void theUserChecksTheCurrentBalance(String userId) {
        String walletId = testContext.getWalletIdForUser(userId);
        Wallet wallet = walletService.findWalletById(UUID.fromString(walletId));
        Assertions.assertNotNull(wallet, "Wallet not found for user " + userId);
        testContext.setLastQueriedBalance(userId, wallet.getBalance().getValue());
    }

    @When("the user {string} checks the balance at {string}")
    public void theUserChecksTheBalanceAt(String userId, String dateTime) {
        String walletId = testContext.getWalletIdForUser(userId);
        LocalDateTime timestamp = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Amount historicalBalance = walletService.findHistoricalBalance(UUID.fromString(walletId), timestamp);
        testContext.setLastQueriedBalance(userId, historicalBalance.getValue());
    }

    @When("the user {string} deposits {double}")
    public void theUserDeposits(String userId, double amount) {
        String walletId = testContext.getWalletIdForUser(userId);
        Amount depositAmount = new Amount(amount);
        Wallet updatedWallet = walletService.deposit(UUID.fromString(walletId), depositAmount);
        testContext.setLastQueriedBalance(userId, updatedWallet.getBalance().getValue());
    }

    @When("the user {string} withdraws {double}")
    public void theUserWithdraws(String userId, Double amount) {
        String walletId = testContext.getWalletIdForUser(userId);
        Amount amountToWithdraw = new Amount(amount);
        Wallet updatedWallet = walletService.withdraw(UUID.fromString(walletId), amountToWithdraw);
        testContext.setLastQueriedBalance(userId, updatedWallet.getBalance().getValue());

    }

    @When("user {string} transfers {double} to user {string}")
    public void userTransfersToUser(String fromUser, double amount, String toUser) {

        String fromWalletId = testContext.getWalletIdForUser(fromUser);
        String toWalletId = testContext.getWalletIdForUser(toUser);
        Amount amountToTransfer = new Amount(amount);
        walletService.transfer(UUID.fromString(fromWalletId), UUID.fromString(toWalletId), amountToTransfer);
    }

    @Then("a wallet is successfully created for user {string}")
    public void aWalletIsSuccessfullyCreatedForUser(String userId) {

        String walletId = testContext.getWalletIdForUser(userId);
        Wallet wallet = walletService.findWalletById(UUID.fromString(walletId));
        Assertions.assertNotNull(wallet, "Expected wallet to be created but none found.");
        Assertions.assertEquals(userId, wallet.getUserId().toString(), "UserID mismatch in created wallet.");
    }

    @Then("the user {string} wallet balance should be {int}")
    public void theUserWalletBalanceShouldBe(String userId, int expectedBalance) {
        String walletId = testContext.getWalletIdForUser(userId);
        Wallet wallet = walletService.findWalletById(UUID.fromString(walletId));
        Assertions.assertNotNull(wallet, "No wallet found for user " + userId);
        Assertions.assertEquals(expectedBalance, wallet.getBalance().getValue(),
                "Unexpected wallet balance for user " + userId);
    }

    @Then("the system should report a balance of {int} for user {string}")
    public void theSystemShouldReportAHistoricalBalanceOf(double expectedBalance, String userId) {

        double lastBalance = testContext.getLastQueriedBalance(userId);
        Assertions.assertEquals(expectedBalance, lastBalance,
                "Historical balance mismatch: expected " + expectedBalance + " but found " + lastBalance);
    }
}


