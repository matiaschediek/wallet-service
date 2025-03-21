package mchediek.wallet_service.infrastructure.web.dtos;

import mchediek.wallet_service.domain.entities.TransactionType;

import java.util.UUID;

public class TransactionRequestDTO {
    private TransactionType type;
    private double amount;
    private UUID walletTo;

    public TransactionRequestDTO(TransactionType type, double amount, UUID walletTo) {
        this.type = type;
        this.amount = amount;
        this.walletTo = walletTo;
    }
    public TransactionType getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public UUID getWalletTo() {
        return walletTo;
    }
}
