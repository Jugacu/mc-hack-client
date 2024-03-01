package es.jugacu.features.impl.hacks;

import es.jugacu.events.Event;
import es.jugacu.events.EventType;
import es.jugacu.features.AbstractFeature;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends AbstractFeature {
    private final MinecraftClient client = MinecraftClient.getInstance();
    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Event(EventType.MIXIN_PLAYER_TICK)
    public void onTick() {
        if (client.player.fallDistance <= 2)
            return;

        // send packet to stop fall damage
        client.player.networkHandler
                .sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
    }

    @Override
    public String getName() {
        return "No fall";
    }

    @Override
    public String getDescription() {
        return "Avoid fall damage";
    }

    @Override
    public boolean canBeEnabled() {
        return true;
    }

    @Override
    public boolean canBeDisabled() {
        return true;
    }
}
