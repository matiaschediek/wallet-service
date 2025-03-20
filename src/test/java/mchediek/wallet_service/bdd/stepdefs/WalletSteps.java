package mchediek.wallet_service.bdd.stepdefs;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class WalletSteps {

    @Given("a user {string} does not yet have a wallet")
    public void aUserDoesNotYetHaveAWallet(String userId) {
    }

    @Given("a user {string} has an existing wallet with a balance of {int}")
    public void aUserHasAnExistingWalletWithABalanceOf(String userId, int balance) {
    }

    @Given("the wallet's balance was {int} at {string}")
    public void theWalletSBalanceWasAt(int balance, String dateTime) {
    }

    @When("the user {string} requests a new wallet")
    public void theUserRequestsANewWallet(String userId) {
    }

    @When("the user {string} checks the current balance")
    public void theUserChecksTheCurrentBalance(String userId) {
    }

    @When("the user {string} checks the balance at {string}")
    public void theUserChecksTheBalanceAt(String userId, String dateTime) {
    }

    @When("the user {string} deposits {int}")
    public void theUserDeposits(String userId, int amount) {
    }

    @When("the user {string} withdraws {int}")
    public void theUserWithdraws(String userId, int amount) {
    }

    @When("user {string} transfers {int} to user {string}")
    public void userTransfersToUser(String fromUser, int amount, String toUser) {
    }

    @Then("a wallet is successfully created for user {string}")
    public void aWalletIsSuccessfullyCreatedForUser(String userId) {
    }

    @Then("the user {string} wallet balance should be {int}")
    public void theUserWalletBalanceShouldBe(String userId, int expectedBalance) {
    }

    @Then("the system should report a balance of {int}")
    public void theSystemShouldReportAHistoricalBalanceOf(int expectedBalance) {
    }
}
