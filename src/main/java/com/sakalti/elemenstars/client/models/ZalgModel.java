package com.sakalti.elemenstars.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.elemenstars.entities.ZalgEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.MultiBufferSource;

public class ZalgModel extends EntityModel<ZalgEntity> {
    private final ModelPart body;

    public ZalgModel() {
        this.body = new ModelPart(this, 0, 0);
        this.body.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8); // ボックスモデルを生成
        this.body.setPos(0.0F, 24.0F, 0.0F); // モデルの位置設定
    }

    @Override
    public void setupAnim(ZalgEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // 必要であればアニメーションを設定
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, buffer.getBuffer(net.minecraft.client.renderer.RenderType.entityCutoutNoCull(getTextureLocation())), packedLight, packedOverlay, red, green, blue, alpha);
    }

    public ResourceLocation getTextureLocation() {
        return new ResourceLocation("elemenstars", "textures/entity/zalg.png");
    }
}
