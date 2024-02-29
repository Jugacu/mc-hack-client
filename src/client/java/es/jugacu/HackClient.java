package es.jugacu;

import es.jugacu.features.FeatureList;
import es.jugacu.features.KeyboardFeature;
import es.jugacu.features.OverlayFeature;
import es.jugacu.features.hud.InGameHud;
import net.fabricmc.api.ClientModInitializer;
public class HackClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FeatureList.getInstance()
				.registerFeature(new OverlayFeature(), false)

				// Auto-enabled features.
				.registerFeature(new InGameHud(), true)
				.registerFeature(new KeyboardFeature(), true);
	}
}