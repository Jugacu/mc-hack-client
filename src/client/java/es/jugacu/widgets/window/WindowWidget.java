package es.jugacu.widgets.window;

import com.mojang.blaze3d.systems.RenderSystem;
import es.jugacu.widgets.FeatureWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class WindowWidget extends ClickableWidget {

    public static final int TITLE_HEIGHT = 20;
    public final MinecraftClient client = MinecraftClient.getInstance();
    private int dragOffsetX;
    private int dragOffsetY;
    private boolean dragging;
    protected final WindowContainerWidget windowContainerWidget;

    public final Color TITLE_COLOR = new Color(0xEA525D5D, true);
    public final Color LINE_COLOR = new Color(0xEA3E4646, true);

    public WindowWidget(
            int x,
            int y,
            int width,
            int height,
            Text text,
            ArrayList<FeatureWidget> featureWidgets
    ) {
        super(x, y, width, height, text);

        this.windowContainerWidget = new WindowContainerWidget(featureWidgets);
    }

    private int[] getTitleBounds() {
        return new int[]{
                this.getX(), this.getY(),
                this.getX() + this.getWidth(), this.getY() + TITLE_HEIGHT
        };
    }


    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        // Title
        int[] titleBounds = getTitleBounds();

        context.fill(
                titleBounds[0], titleBounds[1],
                titleBounds[2], titleBounds[3],
                TITLE_COLOR.getRGB()
        );

        //                        top              bottom
        int verticalCenter = (titleBounds[1] + titleBounds[3]) / 2;

        context.drawText(
                client.textRenderer,
                this.getMessage(),
                titleBounds[0] + 5,
                verticalCenter - client.textRenderer.fontHeight / 2,
                Color.WHITE.getRGB(),
                true
        );

        // Scroll container
        windowContainerWidget.setX(this.getX());
        windowContainerWidget.setWidth(this.getWidth());
        windowContainerWidget.setY(this.getY() + TITLE_HEIGHT);
        windowContainerWidget.setHeight(this.getHeight() - TITLE_HEIGHT);

        windowContainerWidget.renderWidget(context, mouseX, mouseY, delta);

        // Lines
        context.drawHorizontalLine(this.getX(), this.getX() + this.getWidth() - 1, titleBounds[3] - 1, LINE_COLOR.getRGB());
//        context.drawHorizontalLine(this.getX(), this.getX() + this.getWidth() - 1, this.getY(), LINE_COLOR.getRGB());
//        context.drawHorizontalLine(this.getX(), this.getX() + this.getWidth(), this.getY() + getHeight(), LINE_COLOR.getRGB());
//        context.drawVerticalLine(this.getX(), this.getY(),this.getY() + this.getHeight(), LINE_COLOR.getRGB());
//        context.drawVerticalLine(this.getX() + this.getWidth(), this.getY(),this.getY() + this.getHeight(), LINE_COLOR.getRGB());
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int[] bounds = getTitleBounds();

        boolean inXBounds = mouseX >= bounds[0] && mouseX <= bounds[2];
        boolean inYBounds = mouseY >= bounds[1] && mouseY <= bounds[3];

        if (!inXBounds || !inYBounds) {
            windowContainerWidget.mouseClicked(mouseX, mouseY, button);

            return super.mouseClicked(mouseX, mouseY, button);
        }

        dragging = true;

        dragOffsetX = this.getX() - (int) mouseX;
        dragOffsetY = this.getY() - (int) mouseY;

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        windowContainerWidget.mouseReleased(mouseX, mouseY, button);

        dragging = false;

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        if (!dragging) {
            return;
        }

        this.setX((int) mouseX + dragOffsetX);
        this.setY((int) mouseY + dragOffsetY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        windowContainerWidget.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);

        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        windowContainerWidget.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public void playDownSound(SoundManager soundManager) {

    }
}
