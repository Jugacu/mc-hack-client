package es.jugacu.features;

import es.jugacu.screens.OverlayScreen;
import net.minecraft.client.MinecraftClient;

public class OverlayFeature implements Feature {
    public final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onEnable() {
        client.setScreen(new OverlayScreen());
    }

    @Override
    public void onDisable() {
        client.setScreen(null);
    }

    @Override
    public boolean canBeEnabled() {
        return client.world != null;
    }

    @Override
    public boolean canBeDisabled() {
        return true;
    }
}
