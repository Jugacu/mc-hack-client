package es.jugacu;

import es.jugacu.events.Event;
import es.jugacu.events.EventType;
import es.jugacu.screens.OverlayScreen;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class KeyboardManager {
    private static KeyboardManager instance;

    public static KeyboardManager getInstance() {
        if (instance == null) {
            instance = new KeyboardManager();
        }

        return instance;
    }

    private KeyboardManager() {
    }

    @Event(type = EventType.KEY_PRESS)
    public void onKeyPress(long windowHandle, int key, int scancode, int action,
                      int modifiers) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (action != GLFW.GLFW_PRESS) {
            return;
        }

        if (key == GLFW.GLFW_KEY_GRAVE_ACCENT) {
            if (client.world == null) {
                return;
            }

            if (client.currentScreen instanceof OverlayScreen) {
                client.setScreen(null);

                return;
            }

            client.setScreen(new OverlayScreen());
        }
    }
}
