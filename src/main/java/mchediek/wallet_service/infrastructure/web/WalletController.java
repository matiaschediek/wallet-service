package mchediek.wallet_service.infrastructure.web;


import mchediek.wallet_service.application.WalletManagementService;
import mchediek.wallet_service.domain.entities.Amount;
import mchediek.wallet_service.domain.entities.Wallet;
import mchediek.wallet_service.infrastructure.web.dtos.TransactionRequestDTO;
import mchediek.wallet_service.infrastructure.web.dtos.WalletDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletManagementService walletService;

    public WalletController(WalletManagementService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/")
    public ResponseEntity<Wallet> createWallet(@RequestBody WalletDto requestedWallet) {
        Wallet wallet = walletService.createWallet(requestedWallet.getUserId(), new Amount(requestedWallet.getBalance()));
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/")
    public ResponseEntity<Wallet> getCurrentBalance(@RequestParam UUID userId) {
        Wallet wallet = walletService.findWalletByUserId(userId);
        return wallet != null ? ResponseEntity.ok(wallet) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{walletId}/history")
    public ResponseEntity<Double> getHistoricalBalance(
            @PathVariable UUID walletId,
            @RequestParam String timestamp
    ) {
        LocalDateTime dateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME);
        Double historicalBalance = walletService.findHistoricalBalance(walletId, dateTime).getValue();
        return ResponseEntity.ok(historicalBalance);
    }

    @PostMapping("/{walletId}/transaction")
    public ResponseEntity<Wallet> performTransaction(
            @PathVariable UUID walletId,
            @RequestParam TransactionRequestDTO transactionRequest
            ) {
        return switch (transactionRequest.getType()) {
            case DEPOSIT ->
                    ResponseEntity.ok(walletService.deposit(walletId, new Amount(transactionRequest.getAmount())));
            case WITHDRAWAL ->
                    ResponseEntity.ok(walletService.withdraw(walletId, new Amount(transactionRequest.getAmount())));
            case TRANSFER ->
                    ResponseEntity.ok(walletService.transfer(walletId, transactionRequest.getWalletTo(), new Amount(transactionRequest.getAmount())));
        };
    }

}