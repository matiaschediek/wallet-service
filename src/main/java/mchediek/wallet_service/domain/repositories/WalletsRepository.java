package mchediek.wallet_service.domain.repositories;

import mchediek.wallet_service.domain.entities.Wallet;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface WalletsRepository {
    Wallet findWalletByUserId(UUID userId);

    Wallet save(Wallet wallet);

    Wallet findWalletById(UUID walletId);
}
