package mchediek.wallet_service.application;

import mchediek.wallet_service.domain.entities.Amount;
import mchediek.wallet_service.domain.entities.Transaction;
import mchediek.wallet_service.domain.entities.TransactionType;
import mchediek.wallet_service.domain.entities.Wallet;
import mchediek.wallet_service.domain.repositories.TransactionsRepository;
import mchediek.wallet_service.domain.repositories.WalletsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WalletManagementService {

    private final TransactionsRepository transactionsRepository;

    private final WalletsRepository walletsRepository;

    public WalletManagementService(TransactionsRepository transactionsRepository, WalletsRepository walletsRepository) {
        this.transactionsRepository = transactionsRepository;
        this.walletsRepository = walletsRepository;
    }

    public Wallet findWalletByUserId(UUID userId) {
        return walletsRepository.findWalletByUserId(userId);
    }

    public Wallet createWallet(UUID userId, Amount balance) {
        Wallet existingWallet = findWalletByUserId(userId);
        if (existingWallet != null) {
            throw new IllegalStateException("User already has a wallet");
        }
        Wallet wallet = new Wallet(balance, userId);

        walletsRepository.save(wallet);
        return wallet;
    }

    public Wallet findWalletById(UUID walletId) {
        return walletsRepository.findWalletById(walletId);
    }

    public Amount findHistoricalBalance(UUID walletId, LocalDateTime timestamp) {
        Transaction transaction = transactionsRepository.findByWalletIdAndTimestamp(walletId, timestamp);
        return transaction.getBalance();
    }

    public Wallet deposit(UUID walletId, Amount depositAmount) {
        Wallet wallet = findWalletById(walletId).deposit(depositAmount);
        walletsRepository.save(wallet);
        Transaction transaction = new Transaction(walletId, depositAmount, wallet.getBalance(), TransactionType.DEPOSIT, LocalDateTime.now());
        transactionsRepository.save(transaction);
        return wallet;
    }

    public Wallet withdraw(UUID walletId, Amount withdrawAmount) {
        Wallet wallet = findWalletById(walletId).withdraw(withdrawAmount);
        walletsRepository.save(wallet);
        Transaction transaction = new Transaction(walletId, withdrawAmount, wallet.getBalance(), TransactionType.WITHDRAWAL, LocalDateTime.now());
        transactionsRepository.save(transaction);
        return wallet;
    }

    public Wallet transfer(UUID walletId, UUID walletIdTo, Amount amountToTransfer) {

        Wallet wallet = findWalletById(walletId).withdraw(amountToTransfer);
        Wallet walletTo = findWalletById(walletIdTo).deposit(amountToTransfer);
        walletsRepository.save(wallet);
        walletsRepository.save(walletTo);
        Transaction transactionFrom = new Transaction(walletId, amountToTransfer, wallet.getBalance(), TransactionType.TRANSFER, LocalDateTime.now());
        Transaction transactionTo = new Transaction(walletId, amountToTransfer, wallet.getBalance(), TransactionType.TRANSFER, LocalDateTime.now());
        transactionsRepository.save(transactionFrom);
        transactionsRepository.save(transactionTo);
        return wallet;
    }
}
