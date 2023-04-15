package dev.sterner.better_stuck_arrows;


import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;

public class BetterStuckArrows implements ModInitializer {
	public static final TrackedData<Integer> STUCK_SPECTRAL_ARROW_COUNT = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);

	@Override
	public void onInitialize() {

	}
}
