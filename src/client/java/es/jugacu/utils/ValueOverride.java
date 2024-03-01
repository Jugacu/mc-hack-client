package es.jugacu.utils;

public class ValueOverride<T> {
    private T value;

    public ValueOverride(T initialValue) {
        this.value = initialValue;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
