package mchediek.wallet_service.infrastructure.persistence.dtos;

import mchediek.wallet_service.domain.entities.Amount;
import mchediek.wallet_service.domain.entities.Wallet;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("wallets")
public class WalletDocument {

    @Id
    private String id;
    private double balance;
    private String userId;

    public WalletDocument() {}

    public WalletDocument(Wallet domain) {
        this.id = domain.getId().toString();
        this.balance = domain.getBalance().getValue();
        this.userId = domain.getUserId().toString();
    }

    public Wallet toDomain() {
        return new Wallet(
            UUID.fromString(id),
            new Amount(balance),
            UUID.fromString(userId)
        );
    }

}
