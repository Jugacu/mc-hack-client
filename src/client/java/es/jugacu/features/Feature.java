package es.jugacu.features;

public interface Feature {
    void onEnable();
    void onDisable();

    boolean canBeEnabled();

    boolean canBeDisabled();
}
