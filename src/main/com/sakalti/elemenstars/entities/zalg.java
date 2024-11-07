package com.sakalti.elemenstars.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumSet;

public class ZalgEntity extends Monster {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, "elemenstars");

    public static final RegistryObject<EntityType<ZalgEntity>> ZALG = ENTITIES.register("zalg", 
        () -> EntityType.Builder.of(ZalgEntity::new, MobCategory.MONSTER)
            .sized(0.7F, 2.0F) // サイズ設定
            .build("zalg")
    );

    public ZalgEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setHealth(50); // HPを50に設定
    }

    // AIの設定
    @Override
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
    }

    // ダメージの設定（5ダメージ）
    @Override
    public boolean doHurtTarget(Entity target) {
        if (target instanceof Player) {
            target.hurt(DamageSource.mobAttack(this), 5); // ダメージ5
            return true;
        }
        return super.doHurtTarget(target);
    }

    // エンティティ名を設定
    @Override
    public void setCustomName(Component name) {
        super.setCustomName(new TextComponent("Zalg"));
    }

    // AIの補助メソッド
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0) // HP
                .add(Attributes.ATTACK_DAMAGE, 5.0) // 攻撃力
                .add(Attributes.MOVEMENT_SPEED, 0.25); // 移動速度
    }

    // モデルおよびレンダラー定義
    public static class ZalgModel extends EntityModel<ZalgEntity> {
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
        public void renderToBuffer(PoseStack poseStack, net.minecraft.client.renderer.MultiBufferSource buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            body.render(poseStack, buffer.getBuffer(net.minecraft.client.renderer.RenderType.entityCutoutNoCull(getTextureLocation())), packedLight, packedOverlay, red, green, blue, alpha);
        }

        public ResourceLocation getTextureLocation() {
            return new ResourceLocation("elemenstars", "textures/entity/zalg.png");
        }
    }

    public static class ZalgRenderer extends MobRenderer<ZalgEntity, ZalgModel> {
        public ZalgRenderer(EntityRendererProvider.Context context) {
            super(context, new ZalgModel(), 0.5F); // 影のサイズを設定
        }

        @Override
        public ResourceLocation getTextureLocation(ZalgEntity entity) {
            return new ResourceLocation("elemenstars", "textures/entity/zalg.png"); // テクスチャ
        }
    }
}
