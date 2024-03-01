package es.jugacu.features;

import es.jugacu.events.EventRegistry;

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

    private final ArrayList<Feature> features = new ArrayList<>();

    private FeatureList() {
    }

    public FeatureList registerFeature(Feature feature, boolean enabled) {
        features.add(feature);

        if (enabled) {
            enableFeature(feature);
        }

        return this;
    }

    public FeatureList enableFeature(Class<? extends Feature> featureClazz) {
        for (Feature feature : features) {
            if (!featureClazz.isInstance(feature)) {
                continue;
            }

            enableFeature(feature);
        }

        return this;
    }

    public FeatureList enableFeature(Feature feature) {
        if (!feature.canBeEnabled()) {
            return this;
        }

        feature.enable();

        eventRegistry.registerEvents(feature);

        return this;
    }

    public FeatureList disableFeature(Class<? extends Feature> featureClazz) {
        for (Feature feature : features) {
            if (!featureClazz.isInstance(feature)) {
                continue;
            }

            disableFeature(feature);
        }

        return this;
    }

    public FeatureList disableFeature(Feature feature) {
        if (!feature.canBeDisabled()) {
            return this;
        }

        feature.disable();

        eventRegistry.remove(feature);

        return this;
    }

    public boolean isFeatureEnabled(Class<? extends Feature> featureClazz) {
        for (Feature feature : features) {
            if (featureClazz.isInstance(feature)) {
                return feature.isEnabled();
            }
        }

        return false;
    }

    public FeatureList toggleFeature(Class<? extends Feature> featureClazz) {
        for (Feature feature : features) {
            if (featureClazz.isInstance(feature)) {
                if (feature.isEnabled()) {
                    disableFeature(feature);
                } else {
                    enableFeature(feature);
                }
                break;
            }
        }

        return this;
    }

    public Feature getFeatureInstance(Class<? extends Feature> featureClazz) {
        for (Feature feature : features) {
            if(featureClazz.isInstance(feature)) {
                return feature;
            }
        }
        return null;
    }
}
