package tech.challenge.account.service.account.domain.valueobjects;

import lombok.Getter;

import java.util.Objects;
@Getter
public abstract class ValueObject<T> {

    protected final T value;

    protected ValueObject(T value) {
        validar(value);
        this.value = value;
    }

    protected abstract void validar(T value);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject<?> that = (ValueObject<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

