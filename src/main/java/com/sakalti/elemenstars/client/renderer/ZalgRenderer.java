package com.sakalti.elemenstars.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.elemenstars.entities.ZalgEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.MultiBufferSource;

public class ZalgRenderer extends MobRenderer<ZalgEntity, ZalgModel> {
    public ZalgRenderer(EntityRendererProvider.Context context) {
        super(context, new ZalgModel(), 0.5F); // 影のサイズを設定
    }

    @Override
    public ResourceLocation getTextureLocation(ZalgEntity entity) {
        return new ResourceLocation("elemenstars", "textures/entity/zalg.png"); // テクスチャ
    }
}
