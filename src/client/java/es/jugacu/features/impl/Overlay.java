package es.jugacu.features.impl;

import es.jugacu.features.AbstractFeature;
import es.jugacu.screens.overlay.Category;
import es.jugacu.screens.overlay.OverlayScreen;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

public class Overlay extends AbstractFeature {
    @Override
    public String getName() {
        return "Overlay";
    }

    @Override
    public String getDescription() {
        return "The click overlay GUI.";
    }

    public final MinecraftClient client = MinecraftClient.getInstance();

    public final OverlayScreen overlayScreen;

    public Overlay(ArrayList<Category> categories) {
        overlayScreen = new OverlayScreen(categories);
    }

    @Override
    public void onEnable() {
        client.setScreen(overlayScreen);
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
