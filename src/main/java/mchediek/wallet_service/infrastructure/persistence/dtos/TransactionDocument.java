package mchediek.wallet_service.infrastructure.persistence.dtos;

import mchediek.wallet_service.domain.entities.Amount;
import mchediek.wallet_service.domain.entities.Transaction;
import mchediek.wallet_service.domain.entities.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("transactions")
public class TransactionDocument {

    @Id
    private String id;
    private String walletId;
    private double balance;
    private double amount;
    private String type;
    private LocalDateTime timestamp;

    public TransactionDocument() {}

    public TransactionDocument(Transaction domain) {
        this.id = domain.getId().toString();
        this.walletId = domain.getWalletId().toString();
        this.balance = domain.getBalance().getValue();
        this.amount = domain.getAmount().getValue();
        this.type = domain.getType().name();
        this.timestamp = domain.getTimestamp();
    }

    public Transaction toDomain() {
        return new Transaction(
                UUID.fromString(id),
                UUID.fromString(walletId),
                new Amount(balance),
                new Amount(amount),
                TransactionType.valueOf(type),
                timestamp
        );
    }

}