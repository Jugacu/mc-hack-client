package es.jugacu.mixin.client;

import es.jugacu.events.EventRegistry;
import es.jugacu.events.EventType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(
            at = @At(value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V",
                    remap = false,
                    ordinal = 3),
            method = "render(Lnet/minecraft/client/gui/DrawContext;F)V")
    private void onRender(DrawContext context, float tickDelta, CallbackInfo ci)
    {
        EventRegistry.getInstance().fire(EventType.GUI_RENDER, context, tickDelta);
    }

    @Inject(at = @At("HEAD"),
            method = "renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V",
            cancellable = true)
    private void onRenderOverlay(DrawContext context, Identifier texture,
                                 float opacity, CallbackInfo ci)
    {

    }
}
