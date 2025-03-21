package mchediek.wallet_service.infrastructure.web.dtos;

import mchediek.wallet_service.domain.entities.Amount;
import mchediek.wallet_service.domain.entities.Wallet;

import java.util.UUID;

public class WalletDto {
    private UUID id;
    private double balance;
    private UUID userId;

    public WalletDto( double balance, UUID userId) {
        this.balance = balance;
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }
    public UUID getUserId() {
        return userId;
    }
}
