package com.marcp.directauth.mixin;

import com.marcp.directauth.DirectAuth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntityEffects {

    @Inject(method = "tickEffects", at = @At("HEAD"), cancellable = true)
    private void directAuth$freezeEffectsWhileUnauthenticated(CallbackInfo ci) {
        if (((Object) this) instanceof ServerPlayer player) {
            if (DirectAuth.getLoginManager() != null
                    && !DirectAuth.getLoginManager().isAuthenticated(player)) {
                ci.cancel();
            }
        }
    }
}
