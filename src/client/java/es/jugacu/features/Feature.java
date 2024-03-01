package es.jugacu.features;

public interface Feature {
    String getName();
    String getDescription();
    void enable();
    void disable();
    boolean isEnabled();
    boolean canBeEnabled();
    boolean canBeDisabled();
}
