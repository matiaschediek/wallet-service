package mchediek.wallet_service.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final UUID walletId;
    private final Amount balance;
    private final Amount amount;
    private final TransactionType type;
    private final LocalDateTime timestamp;

    public Transaction(UUID walletId, Amount amount, Amount balance, TransactionType type, LocalDateTime timestamp) {
        this(UUID.randomUUID(),walletId,amount,balance,type,timestamp);
    }

    public Transaction(UUID id,UUID walletId, Amount amount, Amount balance, TransactionType type, LocalDateTime timestamp) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.balance = balance;
        this.type = type;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public Amount getBalance() {
        return balance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getId() {
        return id;
    }
    public Amount getAmount() {
        return amount;
    }
    public TransactionType getType() {
        return type;
    }
}
