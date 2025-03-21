package mchediek.wallet_service.domain.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
