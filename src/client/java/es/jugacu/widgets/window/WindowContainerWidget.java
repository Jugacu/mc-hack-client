package es.jugacu.widgets.window;

import es.jugacu.widgets.ScrollableWidget;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.text.Text;

import java.awt.*;

public class WindowContainerWidget extends ScrollableWidget {
    public final Color BG_COLOR = new Color(0x37000000, true);
    public final MinecraftClient client = MinecraftClient.getInstance();

    public WindowContainerWidget() {
        super(0, 0, 0, 0, Text.literal(""));
    }

    @Override
    protected int getContentsHeight() {
        return this.getHeight() + 500;
    }

    @Override
    protected double getDeltaYPerScroll() {
        return 100;
    }

    @Override
    protected void renderContents(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawText(
                client.textRenderer,
                Text.literal("OWO"),
                this.getX() + getPadding(),
                this.getY() + getPadding(),
                Color.WHITE.getRGB(),
                true
        );
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    protected void drawBox(DrawContext context, int x, int y, int width, int height) {
        context.fill(
                this.getX(), this.getY(),
                this.getX() + this.getWidth(), this.getY() + this.getHeight(),
                BG_COLOR.getRGB()
        );
    }
}
