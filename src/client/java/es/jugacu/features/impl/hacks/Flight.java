package es.jugacu.features.impl.hacks;

import es.jugacu.events.Event;
import es.jugacu.events.EventType;
import es.jugacu.features.AbstractFeature;
import es.jugacu.utils.ValueOverride;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Flight extends AbstractFeature {
    public final MinecraftClient client = MinecraftClient.getInstance();

    private int tickCounter = 0;

    @Override
    protected void onEnable() {
        tickCounter = 0;
    }

    @Event(EventType.MIXIN_PLAYER_TICK)
    public void onTick() {
        ClientPlayerEntity player = client.player;

        player.getAbilities().flying = false;

        player.setVelocity(0, 0, 0);
        Vec3d velocity = player.getVelocity();

        // Up
        if(client.options.jumpKey.isPressed())
            player.setVelocity(velocity.x, 1f, velocity.z);

        // Down
        if(client.options.sneakKey.isPressed())
            player.setVelocity(velocity.x, -1f, velocity.z);

        if (tickCounter >= 30)
            tickCounter = 0;

        switch(tickCounter)
        {
            case 0 ->
            {
                if (client.options.sneakKey.isPressed())
                    tickCounter = 2;
                else
                    player.setVelocity(velocity.x,
                            -0.07, velocity.z);
            }

            case 1 -> player.setVelocity(velocity.x,
                    0.07, velocity.z);
        }

        tickCounter++;
    }

    @Event(EventType.OVERRIDE_AIR_STRAFE_SPEED)
    public void onGetOffGroundSpeed(ValueOverride<Float> valueOverride) {
        float speed = 1f;

        if(client.options.sneakKey.isPressed())
            speed = 0;

        valueOverride.setValue(speed);
    }

    @Event(EventType.OVERRIDE_IS_TOUCHING_WATER)
    public void onIsTouchingWater(ValueOverride<Boolean> valueOverride) {
        valueOverride.setValue(false);
    }

    @Override
    protected void onDisable() {

    }

    @Override
    public String getName() {
        return "Flight";
    }

    @Override
    public String getDescription() {
        return "Makes you fly!";
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
