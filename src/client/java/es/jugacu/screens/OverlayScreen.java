package es.jugacu.screens;

import es.jugacu.widgets.window.WindowWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

public class OverlayScreen extends Screen {
    public OverlayScreen() {
        super(
            Text.literal("Hcaksss")
        );
    }

    @Override
    protected void init() {
        super.init();

        this.addDrawableChild(
            new TextWidget(
                    10,
                    10,
                    this.textRenderer.getWidth(this.title),
                    this.textRenderer.fontHeight,
                    this.title,
                    this.textRenderer
            )
        );

        this.addDrawableChild(
            new WindowWidget(this.width - 160,  10, 150, 300, Text.literal("Heading"))
        );
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        // Nope
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}
