package es.jugacu;

import es.jugacu.features.FeatureList;
import es.jugacu.features.impl.Keyboard;
import es.jugacu.features.impl.Overlay;
import es.jugacu.features.impl.hacks.Flight;
import es.jugacu.features.impl.hacks.NoFall;
import es.jugacu.features.impl.hud.InGameHud;
import es.jugacu.screens.overlay.Category;
import es.jugacu.screens.overlay.CategoryItem;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HackClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FeatureList.getInstance()
                // Auto-enabled features.
                .registerFeature(new InGameHud(), true)
                .registerFeature(new Keyboard(), true)

                // Hacks
                .registerFeature(new Flight(), false)
                .registerFeature(new NoFall(), false)


                // Last is overlay, so we have access to the registered features.
                .registerFeature(
                        new Overlay(new ArrayList<>(
                                List.of(
                                        new Category(
                                                Text.literal("Misc"),
                                                new ArrayList<>(
                                                        List.of(
                                                                new CategoryItem(Flight.class),
                                                                new CategoryItem(NoFall.class)
                                                        )
                                                )
                                        )
                                )
                        )
                        ), false);

    }
}