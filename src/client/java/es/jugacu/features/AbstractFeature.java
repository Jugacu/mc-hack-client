package es.jugacu.features;

public abstract class AbstractFeature implements Feature {
    boolean enabled = false;

    @Override
    public void enable() {
        this.enabled = true;

        this.onEnable();
    }

    @Override
    public void disable() {
        this.enabled = false;

        this.onDisable();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    protected abstract void onEnable();
    protected abstract void onDisable();
}
