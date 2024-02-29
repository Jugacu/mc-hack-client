package es.jugacu.features.impl.hud;

import es.jugacu.events.Event;
import es.jugacu.events.EventType;
import es.jugacu.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class InGameHud implements Feature {

    @Event(type = EventType.MIXIN_GUI_RENDER)
    public void onGUIRender(DrawContext context, float tickDelta) {
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean canBeEnabled() {
        return true;
    }

    @Override
    public boolean canBeDisabled() {
        return true;
    }
}
