package mchediek.wallet_service.infrastructure.web;

import mchediek.wallet_service.application.WalletManagementService;
import mchediek.wallet_service.domain.entities.Amount;
import mchediek.wallet_service.domain.entities.TransactionType;
import mchediek.wallet_service.domain.entities.Wallet;
import mchediek.wallet_service.infrastructure.web.dtos.TransactionRequestDTO;
import mchediek.wallet_service.infrastructure.web.dtos.WalletDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class WalletControllerTest {

    @Mock
    private WalletManagementService walletService;

    @InjectMocks
    private WalletController walletController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWallet_createsNewWallet() {
        UUID userId = UUID.randomUUID();
        UUID walletId = UUID.randomUUID();
        Amount balance = new Amount(0d);
        WalletDto walletDto = new WalletDto(balance.getValue(),userId);
        Wallet wallet = new Wallet(walletId, balance,userId);
        Mockito.when(walletService.createWallet(eq(userId), eq(balance))).thenReturn(wallet);

        ResponseEntity<Wallet> response = walletController.createWallet(walletDto);

        Assertions.assertEquals(ResponseEntity.ok(wallet), response);
    }

    @Test
    void getCurrentBalance_returnsWalletIfExists() {
        UUID userId = UUID.randomUUID();
        Wallet wallet = new Wallet(UUID.randomUUID(), new Amount(100d), userId);
        Mockito.when(walletService.findWalletByUserId(eq(userId))).thenReturn(wallet);

        ResponseEntity<Wallet> response = walletController.getCurrentBalance(userId);

        Assertions.assertEquals(ResponseEntity.ok(wallet), response);
    }

    @Test
    void getCurrentBalance_returnsNotFoundIfWalletDoesNotExist() {
        UUID userId = UUID.randomUUID();
        Mockito.when(walletService.findWalletByUserId(userId)).thenReturn(null);

        ResponseEntity<Wallet> response = walletController.getCurrentBalance(userId);

        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void getHistoricalBalance_returnsHistoricalBalance() {
        UUID walletId = UUID.randomUUID();
        String timestamp = "2023-01-01T10:00:00";
        LocalDateTime dateTime = LocalDateTime.parse(timestamp);
        Amount amount = new Amount(100d);
        Mockito.when(walletService.findHistoricalBalance(walletId, dateTime)).thenReturn(amount);

        ResponseEntity<Double> response = walletController.getHistoricalBalance(walletId, timestamp);

        Assertions.assertEquals(ResponseEntity.ok(amount.getValue()), response);
    }

    @Test
    void deposit_depositsAmount() {
        UUID walletId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        TransactionRequestDTO transactionRequest = new TransactionRequestDTO(TransactionType.DEPOSIT, 100d, null);
        Wallet wallet = new Wallet(walletId, new Amount(150d),userId);
        Mockito.when(walletService.deposit(walletId, new Amount(transactionRequest.getAmount()))).thenReturn(wallet);

        ResponseEntity<Wallet> response = walletController.performTransaction(walletId, transactionRequest);

        Assertions.assertEquals(ResponseEntity.ok(wallet), response);
        verify(walletService).deposit(walletId, new Amount(transactionRequest.getAmount()));
    }

    @Test
    void deposit_withdrawsAmount() {
        UUID walletId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        TransactionRequestDTO transactionRequest = new TransactionRequestDTO(TransactionType.WITHDRAWAL, 100d, null);
        Wallet wallet = new Wallet(walletId, new Amount(50d),userId);
        Mockito.when(walletService.withdraw(walletId, new Amount(transactionRequest.getAmount()))).thenReturn(wallet);

        ResponseEntity<Wallet> response = walletController.performTransaction(walletId, transactionRequest);

        Assertions.assertEquals(ResponseEntity.ok(wallet), response);
        verify(walletService).withdraw(walletId, new Amount(transactionRequest.getAmount()));
    }

    @Test
    void deposit_transfersAmount() {
        UUID walletId = UUID.randomUUID();
        UUID walletToId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        TransactionRequestDTO transactionRequest = new TransactionRequestDTO(TransactionType.TRANSFER, 100d, walletToId);
        Wallet wallet = new Wallet(walletId, new Amount(50d),userId);
        Mockito.when(walletService.transfer(walletId, walletToId, new Amount(transactionRequest.getAmount()))).thenReturn(wallet);

        ResponseEntity<Wallet> response = walletController.performTransaction(walletId, transactionRequest);

        Assertions.assertEquals(ResponseEntity.ok(wallet), response);
        verify(walletService).transfer(walletId, walletToId, new Amount(transactionRequest.getAmount()));
    }

}