package dev.sterner.better_stuck_arrows.mixin;

import dev.sterner.better_stuck_arrows.BetterStuckArrows;
import dev.sterner.better_stuck_arrows.ISpectralArrow;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ISpectralArrow {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void betterStuckArrow$initDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(BetterStuckArrows.STUCK_SPECTRAL_ARROW_COUNT, 0);
    }

    @Override
    public final int getStuckSpectralArrowCount() {
        return this.dataTracker.get(BetterStuckArrows.STUCK_SPECTRAL_ARROW_COUNT);
    }

    @Override
    public final void setStuckSpectralArrowCount(int stuckArrowCount) {
        this.dataTracker.set(BetterStuckArrows.STUCK_SPECTRAL_ARROW_COUNT, stuckArrowCount);
    }
}
