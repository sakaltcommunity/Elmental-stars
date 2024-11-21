package com.sakalti.elemenstars.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.elemenstars.entities.IceSlimeEntity;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class IceSlimeRenderer extends EntityRenderer<IceSlimeEntity> {
    private final SlimeModel<IceSlimeEntity> model;

    public IceSlimeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SlimeModel<>(context.bakeLayer(SlimeModel.LAYER_LOCATION));
    }

    @Override
    public void render(IceSlimeEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, net.minecraft.client.renderer.MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        this.model.renderToBuffer(poseStack, bufferSource.getBuffer(this.model.renderType(this.getTextureLocation(entity))), packedLight, entity.getColor(), 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(IceSlimeEntity entity) {
        return new ResourceLocation("elemenstars", "textures/entity/ice_slime.png");
    }
}
