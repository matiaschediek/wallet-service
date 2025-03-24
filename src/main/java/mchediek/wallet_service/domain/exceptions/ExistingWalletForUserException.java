package mchediek.wallet_service.domain.exceptions;

public class ExistingWalletForUserException extends DomainException {

    public ExistingWalletForUserException() {
        super("EXISTING_WALLET_FOR_USER", "User already has a wallet");
    }
}
