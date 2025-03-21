package mchediek.wallet_service.domain.repositories;

import mchediek.wallet_service.domain.entities.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionsRepository {
    Transaction save(Transaction transaction);
    Transaction findByWalletIdAndTimestamp(UUID walletId, LocalDateTime timestamp);
}
