package dev.sterner.better_stuck_arrows.mixin;

import dev.sterner.better_stuck_arrows.ISpectralArrow;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {


    @Redirect(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setStuckArrowCount(I)V"))
    private void better_stuck_arrows$onEntityHit(LivingEntity instance, int stuckArrowCount) {
        PersistentProjectileEntity entity = PersistentProjectileEntity.class.cast(this);
        if (entity instanceof SpectralArrowEntity && instance instanceof PlayerEntity player && player instanceof ISpectralArrow) {
            ((ISpectralArrow) player).setStuckSpectralArrowCount(((ISpectralArrow) player).getStuckSpectralArrowCount() + 1);
        } else {
            instance.setStuckArrowCount(instance.getStuckArrowCount() + 1);
        }
    }
}
