package es.jugacu.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;

public class OverlayScreen extends Screen {
    public OverlayScreen() {
        super(
            Text.literal("Hcaksss")
        );
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawText(
                textRenderer,
                this.title,
                10,
                10,
                Color.WHITE.getRGB(),
        true
        );
    }
}
