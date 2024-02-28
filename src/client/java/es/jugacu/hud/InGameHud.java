package es.jugacu.hud;

import es.jugacu.events.Event;
import es.jugacu.events.EventType;
import net.minecraft.client.gui.DrawContext;

public class InGameHud {
    private static InGameHud instance;

    public static InGameHud getInstance() {
        if (instance == null) {
            instance = new InGameHud();
        }

        return instance;
    }

    private InGameHud() {
    }

    @Event(type = EventType.GUI_RENDER)
    public void onGUIRender(DrawContext context, float tickDelta) {
    }
}
