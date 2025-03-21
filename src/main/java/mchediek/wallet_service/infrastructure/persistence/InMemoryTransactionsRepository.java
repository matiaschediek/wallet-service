package mchediek.wallet_service.infrastructure.persistence;

import mchediek.wallet_service.domain.entities.Transaction;
import mchediek.wallet_service.domain.repositories.TransactionsRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryTransactionsRepository implements TransactionsRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public Transaction save(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public Transaction findByWalletIdAndTimestamp(UUID walletId, LocalDateTime timestamp) {
        return transactions.stream()
                .filter(tx ->
                        tx.getWalletId().equals(walletId) &&
                                tx.getTimestamp().equals(timestamp))
                .findFirst()
                .orElse(null);
    }
}