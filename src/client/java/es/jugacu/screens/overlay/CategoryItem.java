package es.jugacu.screens.overlay;

import es.jugacu.features.Feature;
import es.jugacu.features.FeatureList;

public class CategoryItem {
    private final Feature feature;

    public CategoryItem(Class<? extends Feature> featureClazz) {
        this.feature = FeatureList.getInstance()
                .getFeatureInstance(featureClazz);
    }

    public Feature getFeature() {
        return feature;
    }
}
