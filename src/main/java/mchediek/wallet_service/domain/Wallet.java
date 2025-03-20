package mchediek.wallet_service.domain;

import java.util.UUID;

public class Wallet {
    private UUID id;
    private Amount balance;
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public Amount getBalance() {
        return balance;
    }

    public UUID getUserId() {
        return userId;
    }
}
