package mchediek.wallet_service.domain;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionManagementService {
    public void recordBalance(UUID walletId, Amount balance, LocalDateTime timestamp) {
    }
}
