package es.jugacu.widgets.window;

import es.jugacu.features.FeatureList;
import es.jugacu.features.impl.Keyboard;
import es.jugacu.widgets.FeatureWidget;
import es.jugacu.widgets.ScrollableWidget;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class WindowContainerWidget extends ScrollableWidget {
    public final int MARGIN_BOTTOM = 2;
    public final int PADDING = 2;
    public final Color BG_COLOR = new Color(0x37000000, true);

    public final MinecraftClient client = MinecraftClient.getInstance();
    public final ArrayList<FeatureWidget> featureWidgets;

    public WindowContainerWidget(ArrayList<FeatureWidget> featureWidgets) {
        super(0, 0, 0, 0, Text.literal(""));

        this.featureWidgets = featureWidgets;
    }

    @Override
    protected int getContentsHeight() {
        return Math.max(this.featureWidgets.size() * FeatureWidget.HEIGHT + MARGIN_BOTTOM, this.getHeight());
    }

    @Override
    protected double getDeltaYPerScroll() {
        return 100;
    }

    @Override
    protected void renderContents(DrawContext context, int mouseX, int mouseY, float delta) {
        for (int i = 0, featureWidgetsSize = featureWidgets.size(); i < featureWidgetsSize; i++) {
            FeatureWidget featureWidget = featureWidgets.get(i);

            featureWidget.setWidth(this.getWidth() - this.getPadding() * 2 - (this.overflows() ? this.getScrollerWidth() : 0));

            featureWidget.setX(this.getX() + this.getPadding());
            featureWidget.setY(this.getY() + this.getPadding() + i * (featureWidget.getHeight() + MARGIN_BOTTOM));

            featureWidget.render(context, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (FeatureWidget featureWidget : featureWidgets) {
            featureWidget.mouseClicked(mouseX, mouseY + this.getScrollY(), button);
        }

        return super.mouseClicked(mouseX, mouseY, button);
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

    @Override
    public void playDownSound(SoundManager soundManager) {
    }
}
