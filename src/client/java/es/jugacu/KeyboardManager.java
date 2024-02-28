package es.jugacu;

import es.jugacu.screens.OverlayScreen;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

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

    public void onKeyPress(long windowHandle, int key, int scancode, int action,
                      int modifiers) {
        if (action != GLFW.GLFW_PRESS) {
            return;
        }

        if (key == GLFW.GLFW_KEY_GRAVE_ACCENT) {
            if (MinecraftClient.getInstance().world == null) {
                return;
            }

            MinecraftClient.getInstance().setScreen(new OverlayScreen());
        }
    }
}
