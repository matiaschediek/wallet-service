package mchediek.wallet_service.domain;

public class Amount {
    private final Double value;

    public Amount(Double value) {
        this.value = value;
    }
    public Amount() {
        this.value = 0d;
    }

    public Double getValue() {
        return value;
    }
}
