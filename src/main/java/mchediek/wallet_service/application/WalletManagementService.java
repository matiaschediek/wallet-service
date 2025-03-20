package mchediek.wallet_service.application;

import mchediek.wallet_service.domain.Amount;
import mchediek.wallet_service.domain.Wallet;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WalletManagementService {
    public Wallet findWalletByUserId(UUID userId) {
        return new Wallet();
    }

    public Wallet createOrUpdateWallet(UUID userId, Amount balance) {
        return new Wallet();
    }

    public Wallet findWalletById(UUID walletId) {
        return new Wallet();
    }

    public Amount findHistoricalBalance(UUID walletId, LocalDateTime timestamp) {
        return new Amount();
    }

    public Wallet deposit(UUID walletId, Amount depositAmount) {
        return new Wallet();
    }

    public Wallet withdraw(UUID walletId, Amount amount) {
        return new Wallet();
    }

    public Wallet transfer(UUID walletId, UUID walletIdTo, Amount amountToTransfer) {
        return new Wallet();
    }
}
