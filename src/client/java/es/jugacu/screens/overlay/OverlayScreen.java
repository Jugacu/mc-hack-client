package es.jugacu.screens.overlay;

import es.jugacu.widgets.FeatureWidget;
import es.jugacu.widgets.window.WindowWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class OverlayScreen extends Screen {
    private final ArrayList<Category> categories;

    public OverlayScreen(ArrayList<Category> categories) {
        super(Text.literal("Hcaksss"));

        this.categories = categories;
    }

    private ArrayList<WindowWidget> createWindowWidgets() {
        ArrayList<WindowWidget> widgets = new ArrayList<>();

        for (int i = categories.size() - 1; i >= 0; i--) {
            Category category = categories.get(i);

            widgets.add(
                    new WindowWidget(
                            this.width - 160 - i * 160,
                            10,
                            150,
                            100,
                            category.getName(),
                            new ArrayList<>(
                                category.items.stream().map(item -> new FeatureWidget(item.getFeature())).toList()
                            )
                    )
            );

        }

        return widgets;
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

        for (WindowWidget widget : createWindowWidgets()) {
            this.addDrawableChild(
                    widget
            );
        }
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
