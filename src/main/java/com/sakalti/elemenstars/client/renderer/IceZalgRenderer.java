package com.sakalti.elemenstars.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ZalgModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.EntityModel;

public class IceZalgRenderer extends EntityRenderer<IceZalgEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("elemenstars", "textures/entity/ice_zalg.png");

    public IceZalgRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(IceZalgEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        ZalgModel<IceZalgEntity> model = new ZalgModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ZalgModel.LAYER_LOCATION));
        model.renderToBuffer(poseStack, bufferSource.getBuffer(model.renderType(TEXTURE)), packedLight, entity.getColorMultiplier(), 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(IceZalgEntity entity) {
        return TEXTURE;
    }
}
