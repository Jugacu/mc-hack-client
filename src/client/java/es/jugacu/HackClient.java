package es.jugacu;

import es.jugacu.events.EventRegistry;
import es.jugacu.features.FeatureList;
import net.fabricmc.api.ClientModInitializer;
public class HackClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EventRegistry eventRegistry = EventRegistry.getInstance();

		eventRegistry.registerEvents(KeyboardManager.getInstance());
	}
}