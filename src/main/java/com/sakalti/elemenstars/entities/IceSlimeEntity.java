package com.sakalti.elemenstars.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

import net.minecraft.client.renderer.entity.RendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import java.util.EnumSet;

public class IceSlimeEntity extends Monster {

    public static final EntityType<IceSlimeEntity> ICE_SLIME = EntityType.Builder.of(IceSlimeEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 0.6F)
            .build("ice_slime");

    private static final double DAMAGE = 6.0; // Normalでの攻撃ダメージ

    public IceSlimeEntity(EntityType<? extends Monster> p_32922_, Level p_32923_) {
        super(p_32922_, p_32923_);
        this.xpReward = 5;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // プレイヤーに向かって攻撃
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));

        // プレイヤーを見つめる
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));

        // ランダムに歩き回る
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));

        // マグマを避ける
        this.goalSelector.addGoal(4, new AvoidLavaGoal(this, 2.0D));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level.isClientSide) {
            this.hurt(DamageSource.playerAttack(player), (float) DAMAGE);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public int getHealth() {
        return 60; // HP
    }

    // マグマを避けるためのGoalクラス
    static class AvoidLavaGoal extends Goal {
        private final IceSlimeEntity slime;
        private final double speed;

        public AvoidLavaGoal(IceSlimeEntity slime, double speed) {
            this.slime = slime;
            this.speed = speed;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            // マグマブロックの近くにいる場合
            return this.slime.level.getBlockState(this.slime.blockPosition().below()).is(Blocks.LAVA);
        }

        @Override
        public void start() {
            // マグマから一定距離離れる方向に移動
            Vec3 avoidDirection = this.slime.getLookAngle().scale(-1);
            this.slime.getNavigation().moveTo(this.slime.getX() + avoidDirection.x, this.slime.getY() + avoidDirection.y, this.slime.getZ() + avoidDirection.z, speed);
        }
    }

    // レンダリング設定 (モデル・描画)
    public static class IceSlimeRenderer extends EntityRenderer<IceSlimeEntity> {
        private final EntityModel<IceSlimeEntity> model;

        public IceSlimeRenderer(EntityRendererProvider.Context context) {
            super(context);
            this.model = new SlimeModel<>(context.bakeLayer(SlimeModel.LAYER_LOCATION));
        }

        @Override
        public void render(IceSlimeEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, net.minecraft.client.renderer.MultiBufferSource bufferSource, int packedLight) {
            poseStack.pushPose();
            this.model.renderToBuffer(poseStack, bufferSource.getBuffer(this.model.renderType(this.getTextureLocation(entity))), packedLight, LivingEntity.getColorMultiplier(entity, 0), 1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
        }

        @Override
        public ResourceLocation getTextureLocation(IceSlimeEntity entity) {
            // アイススライムのテクスチャファイルを指定
            return new ResourceLocation("elemenstars", "textures/entity/ice_slime.png");
        }
    }
}
