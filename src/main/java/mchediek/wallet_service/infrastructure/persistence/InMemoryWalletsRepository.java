package mchediek.wallet_service.infrastructure.persistence;

import mchediek.wallet_service.domain.entities.Wallet;
import mchediek.wallet_service.domain.repositories.WalletsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryWalletsRepository implements WalletsRepository {

    private final List<Wallet> wallets = new ArrayList<>();

    @Override
    public Wallet findWalletByUserId(UUID userId) {
        return wallets.stream()
                .filter(w -> w.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Wallet save(Wallet wallet) {
        for (int i = 0; i < wallets.size(); i++) {
            if (wallets.get(i).getId().equals(wallet.getId())) {
                wallets.set(i, wallet);
                return wallet;
            }
        }
        wallets.add(wallet);
        return wallet;
    }

    @Override
    public Wallet findWalletById(UUID walletId) {
        return wallets.stream()
                .filter(w -> w.getId().equals(walletId))
                .findFirst()
                .orElse(null);
    }
}