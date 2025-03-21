package mchediek.wallet_service.domain.entities;

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
    public Amount add(Amount amount) {
        return new Amount(value + amount.getValue());
    }
    public Amount subtract(Amount amount) {
        return new Amount(value - amount.getValue());
    }
}
