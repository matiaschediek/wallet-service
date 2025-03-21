package mchediek.wallet_service.infrastructure.persistence;

import mchediek.wallet_service.domain.entities.Transaction;
import mchediek.wallet_service.domain.repositories.TransactionsRepository;
import mchediek.wallet_service.infrastructure.persistence.dtos.TransactionDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class MongoDBTransactionsRepository implements TransactionsRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoDBTransactionsRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionDocument doc = new TransactionDocument(transaction);
        TransactionDocument saved = mongoTemplate.save(doc);
        return saved.toDomain();
    }

    @Override
    public Transaction findByWalletIdAndTimestamp(UUID walletId, LocalDateTime timestamp) {
        Query query = new Query(Criteria.where("walletId").is(walletId.toString()).and("timestamp").lte(timestamp));
        TransactionDocument doc = mongoTemplate.findOne(query, TransactionDocument.class);
        return doc != null ? doc.toDomain() : null;
    }
}