package es.jugacu.mixin.client;

import com.mojang.authlib.GameProfile;
import es.jugacu.events.EventRegistry;
import es.jugacu.events.EventType;
import es.jugacu.utils.ValueOverride;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V",
            ordinal = 0), method = "tick()V")
    private void onTick(CallbackInfo ci)
    {
        EventRegistry.getInstance()
                .fire(EventType.MIXIN_PLAYER_TICK);
    }

    @Override
    protected float getOffGroundSpeed() {
        ValueOverride<Float> valueOverride = new ValueOverride<>(super.getOffGroundSpeed());

        EventRegistry.getInstance()
                .fire(EventType.OVERRIDE_AIR_STRAFE_SPEED, valueOverride);

        return valueOverride.getValue();
    }

    @Override
    public boolean isTouchingWater() {
        ValueOverride<Boolean> valueOverride = new ValueOverride<Boolean>(super.isTouchingWater());

        EventRegistry.getInstance()
                .fire(EventType.OVERRIDE_IS_TOUCHING_WATER, valueOverride);

        return valueOverride.getValue();
    }
}
