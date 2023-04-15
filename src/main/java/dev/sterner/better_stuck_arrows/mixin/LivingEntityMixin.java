package dev.sterner.better_stuck_arrows.mixin;

import dev.sterner.better_stuck_arrows.ISpectralArrow;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    private int better_stuck_arrows$stuckSpectralArrowTimer;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getStuckArrowCount()I"))
    private void better_stuck_arrows$tick(CallbackInfo ci){
        LivingEntity livingEntity = LivingEntity.class.cast(this);
        if(livingEntity instanceof PlayerEntity player && player instanceof ISpectralArrow){
            int s = ((ISpectralArrow) player).getStuckSpectralArrowCount();
            if (s > 0) {
                if (this.better_stuck_arrows$stuckSpectralArrowTimer <= 0) {
                    this.better_stuck_arrows$stuckSpectralArrowTimer = 20 * (30 - s);
                }
                --this.better_stuck_arrows$stuckSpectralArrowTimer;
                if (this.better_stuck_arrows$stuckSpectralArrowTimer <= 0) {
                    ((ISpectralArrow) player).setStuckSpectralArrowCount(s - 1);
                }
            }
        }
    }
}
