package mchediek.wallet_service.infrastructure.persistence;

import mchediek.wallet_service.domain.entities.Wallet;
import mchediek.wallet_service.domain.repositories.WalletsRepository;
import mchediek.wallet_service.infrastructure.persistence.dtos.WalletDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MongoDBWalletsRepository implements WalletsRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoDBWalletsRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletDocument doc = new WalletDocument(wallet);
        WalletDocument saved = mongoTemplate.save(doc);
        return saved.toDomain();
    }

    @Override
    public Wallet findWalletByUserId(UUID userId) {
        Query query = new Query(Criteria.where("userId").is(userId.toString()));
        WalletDocument doc = mongoTemplate.findOne(query, WalletDocument.class);
        return doc != null ? doc.toDomain() : null;
    }

    @Override
    public Wallet findWalletById(UUID walletId) {
        Query query = new Query(Criteria.where("id").is(walletId.toString()));
        WalletDocument doc = mongoTemplate.findOne(query, WalletDocument.class);
        return doc != null ? doc.toDomain() : null;
    }
}