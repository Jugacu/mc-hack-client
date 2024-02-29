package es.jugacu.features;

import es.jugacu.events.Event;
import es.jugacu.events.EventType;
import org.lwjgl.glfw.GLFW;

public class Keyboard implements Feature {
    @Event(type = EventType.MIXIN_KEY_PRESS)
    public void onKeyPress(long windowHandle, int key, int scancode, int action, int modifiers) {
        if (action != GLFW.GLFW_PRESS) {
            return;
        }

        if (key == GLFW.GLFW_KEY_GRAVE_ACCENT) {
            FeatureList
                    .getInstance()
                    .toggleFeature(Overlay.class);
        }
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
