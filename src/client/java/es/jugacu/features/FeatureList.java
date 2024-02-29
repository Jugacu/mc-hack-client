package es.jugacu.features;

import es.jugacu.events.EventRegistry;
import es.jugacu.features.hud.InGameHud;

import java.util.ArrayList;
import java.util.Iterator;

public class FeatureList {
    private static FeatureList instance;

    public static FeatureList getInstance() {
        if (FeatureList.instance == null) {
            FeatureList.instance = new FeatureList();
        }

        return FeatureList.instance;
    }

    private final EventRegistry eventRegistry = EventRegistry.getInstance();

    private final ArrayList<Feature> disabledFeatures = new ArrayList<>();
    private final ArrayList<Feature> enabledFeatures = new ArrayList<>();

    private FeatureList() {
    }

    public FeatureList registerFeature(Feature feature, boolean enabled) {
        disabledFeatures.add(feature);

        if (enabled) {
            enableFeature(feature.getClass());
        }

        return this;
    }

    public FeatureList enableFeature(Class<? extends Feature> featureClazz) {
        Iterator<Feature> iterator = disabledFeatures.iterator();

        while (iterator.hasNext()) {
            Feature feature = iterator.next();

            if (!featureClazz.isInstance(feature)) {
                continue;
            }

            if (!feature.canBeEnabled()) {
                continue;
            }

            iterator.remove();  // Remove the current element from the list
            enabledFeatures.add(feature);

            feature.onEnable();

            eventRegistry.registerEvents(feature);
        }

        return this;
    }

    public FeatureList disableFeature(Class<? extends Feature> featureClazz) {
        Iterator<Feature> iterator = enabledFeatures.iterator();

        while (iterator.hasNext()) {
            Feature feature = iterator.next();

            if (!featureClazz.isInstance(feature)) {
                continue;
            }

            if (!feature.canBeDisabled()) {
                continue;
            }

            iterator.remove();  // Remove the current element from the list
            disabledFeatures.add(feature);

            feature.onDisable();

            eventRegistry.remove(feature);
        }

        return this;
    }

    public boolean isFeatureEnabled(Class<? extends Feature> featureClazz) {
        return enabledFeatures.stream().anyMatch(featureClazz::isInstance);
    }

    public FeatureList toggleFeature(Class<? extends Feature> featureClazz) {
        if (isFeatureEnabled(featureClazz)) {
           disableFeature(featureClazz);

           return this;
        }

        enableFeature(featureClazz);

        return this;
    }
}
