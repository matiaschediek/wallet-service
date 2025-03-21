package mchediek.wallet_service.domain.entities;

import java.util.UUID;

public class Wallet {
    private final UUID id;
    private final Amount balance;
    private final UUID userId;

    public Wallet(UUID id, Amount balance, UUID userId) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
    }
    public Wallet(Amount balance, UUID userId) {
        this(UUID.randomUUID(), balance, userId);
    }

    public UUID getId() {
        return id;
    }

    public Amount getBalance() {
        return balance;
    }

    public UUID getUserId() {
        return userId;
    }

    public Wallet deposit(Amount amount) {
        return new Wallet(id, balance.add(amount), userId);
    }
    public Wallet withdraw(Amount amount) {
        return new Wallet(id,balance.subtract(amount), userId);
    }

}
