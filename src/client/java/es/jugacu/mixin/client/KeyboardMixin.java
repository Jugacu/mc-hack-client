package es.jugacu.mixin.client;

import es.jugacu.KeyboardManager;
import es.jugacu.events.EventRegistry;
import es.jugacu.events.EventType;
import net.minecraft.client.Keyboard;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
	@Inject(at = @At("HEAD"), method = "onKey")
	private void onKey(long windowHandle, int key, int scancode, int action,
					   int modifiers, CallbackInfo ci) {
		EventRegistry.getInstance().fire(
				EventType.KEY_PRESS,
				windowHandle, key, scancode, action, modifiers
		);

	}
}