package mchediek.wallet_service.infrastructure.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mchediek.wallet_service.application.WalletManagementService;
import mchediek.wallet_service.domain.entities.Amount;
import mchediek.wallet_service.domain.entities.Wallet;
import mchediek.wallet_service.infrastructure.logging.AuditLogger;
import mchediek.wallet_service.infrastructure.web.dtos.ApiError;
import mchediek.wallet_service.infrastructure.web.dtos.TransactionRequestDTO;
import mchediek.wallet_service.infrastructure.web.dtos.WalletDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/wallets")
@Tag(name = "Wallet Management", description = "Operations related to user wallets")
public class WalletController {

    private final WalletManagementService walletService;

    public WalletController(WalletManagementService walletService) {
        this.walletService = walletService;
    }

    @Operation(summary = "Create a new wallet", description = "Creates a wallet for the given user with an initial balance.")
    @ApiResponse(
            responseCode = "200",
            description = "Wallet created successfully"
    )
    @ApiResponse(
            responseCode = "422",
            description = "Domain error: Wallet already exists for the user",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
    )
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody WalletDto requestedWallet) {
        AuditLogger.log("Creating wallet for user " + requestedWallet.getUserId());
        Wallet wallet = walletService.createWallet(requestedWallet.getUserId(), new Amount(requestedWallet.getBalance()));
        return ResponseEntity.ok(wallet);
    }

    @Operation(summary = "Get current wallet by user ID", description = "Returns the current wallet for a given user.")
    @ApiResponse(
            responseCode = "404",
            description = "Wallet not found"
    )
    @GetMapping
    public ResponseEntity<Wallet> getCurrentBalance(@Parameter(description = "UUID of the user") @RequestParam UUID userId) {
        Wallet wallet = walletService.findWalletByUserId(userId);

        return wallet != null ? ResponseEntity.ok(wallet) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get historical balance", description = "Returns the balance of a wallet at a specific timestamp.")
    @GetMapping("/{walletId}/history")
    public ResponseEntity<Double> getHistoricalBalance(
            @Parameter(description = "UUID of the wallet") @PathVariable UUID walletId,
            @Parameter(description = "Timestamp in ISO 8601 format (e.g. 2023-03-21T15:30:00)") @RequestParam String timestamp
    ) {
        LocalDateTime dateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME);
        Double historicalBalance = walletService.findHistoricalBalance(walletId, dateTime).getValue();
        return ResponseEntity.ok(historicalBalance);
    }

    @Operation(summary = "Perform transaction", description = "Executes a deposit, withdrawal, or transfer on the wallet.")
    @PostMapping("/{walletId}/transaction")
    public ResponseEntity<Wallet> performTransaction(
            @Parameter(description = "UUID of the wallet") @PathVariable UUID walletId,
            @RequestBody TransactionRequestDTO transactionRequest
            ) {

        AuditLogger.log("Performing transaction "+ transactionRequest.getType().name() +" on wallet " + walletId + " for amount " + transactionRequest.getAmount());

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