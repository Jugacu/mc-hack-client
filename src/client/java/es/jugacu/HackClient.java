package es.jugacu;

import es.jugacu.features.FeatureList;
import es.jugacu.features.impl.Keyboard;
import es.jugacu.features.impl.Overlay;
import es.jugacu.features.impl.hud.InGameHud;
import net.fabricmc.api.ClientModInitializer;
public class HackClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FeatureList.getInstance()
				.registerFeature(new Overlay(), false)

				// Auto-enabled features.
				.registerFeature(new InGameHud(), true)
				.registerFeature(new Keyboard(), true);
	}
}