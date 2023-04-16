package dev.sterner.better_stuck_arrows;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.StuckObjectsFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class StuckSpectralArrowsFeatureRenderer<T extends LivingEntity, M extends PlayerEntityModel<T>> extends StuckObjectsFeatureRenderer<T, M> {
    private final EntityRenderDispatcher dispatcher;

    public StuckSpectralArrowsFeatureRenderer(EntityRendererFactory.Context context, LivingEntityRenderer<T, M> entityRenderer) {
        super(entityRenderer);
        this.dispatcher = context.getRenderDispatcher();
    }

    protected int getObjectCount(T entity) {
        if (entity instanceof PlayerEntity player && player instanceof ISpectralArrow) {
            return ((ISpectralArrow) player).getStuckSpectralArrowCount();
        }
        return entity.getStuckArrowCount();
    }

    protected void renderObject(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float directionX, float directionY, float directionZ, float tickDelta) {
        float f = MathHelper.sqrt(directionX * directionX + directionZ * directionZ);
        SpectralArrowEntity arrowEntity = new SpectralArrowEntity(entity.world, entity.getX(), entity.getY(), entity.getZ());
        arrowEntity.setYaw((float) (Math.atan2(directionX, directionZ) * 57.2957763671875));
        arrowEntity.setPitch((float) (Math.atan2(directionY, f) * 57.2957763671875));
        arrowEntity.prevYaw = arrowEntity.getYaw();
        arrowEntity.prevPitch = arrowEntity.getPitch();
        this.dispatcher.render(arrowEntity, 0.0, 0.0, 0.0, 0.0F, tickDelta, matrices, vertexConsumers, light);
    }
}