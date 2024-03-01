package es.jugacu.widgets;

import es.jugacu.features.Feature;
import es.jugacu.features.FeatureList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.awt.*;

public class FeatureWidget extends ClickableWidget {
    public static final int HEIGHT = 20;

    public final MinecraftClient client = MinecraftClient.getInstance();

    public final Color BACKGROUND_COLOR = new Color(0x4D000000, true);
    private final Feature feature;

    public FeatureWidget(Feature feature) {
        super(0, 0, 0, HEIGHT, Text.literal(feature.getName()));
        
        this.feature = feature;

        this.setTooltip(Tooltip.of(Text.literal(feature.getDescription())));
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        this.setHeight(20);

        context.drawText(
                client.textRenderer,
                this.getMessage(),
                this.getX() + (this.getWidth() - client.textRenderer.getWidth(this.getMessage())) / 2,
                this.getY() + this.getHeight() / 2 - client.textRenderer.fontHeight / 2,
                feature.isEnabled() ? Color.GREEN.getRGB() : Color.WHITE.getRGB(),
                true
        );


        // Background
        context.fill(
                this.getX(), this.getY(),
                this.getX() + this.getWidth(), this.getY() + this.getHeight(),
                BACKGROUND_COLOR.getRGB()
        );
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean isBoundX = mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth();
        boolean isBoundY = mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight();

        if (!isBoundX || !isBoundY) {
            return false;
        }

        FeatureList.getInstance().toggleFeature(
                this.feature.getClass()
        );

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }
}
