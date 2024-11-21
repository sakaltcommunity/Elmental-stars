package com.sakalti.elemenstars.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class IceSlimeEntity extends Monster {

    public static final EntityType<IceSlimeEntity> ICE_SLIME = EntityType.Builder.of(IceSlimeEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 0.6F) // サイズ設定
            .build("ice_slime");

    public IceSlimeEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true)); // 近接攻撃
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F)); // プレイヤーを見つめる
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D)); // ランダム歩行
        this.goalSelector.addGoal(4, new AvoidLavaGoal(this, 2.0D)); // マグマを避ける
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60.0) // HP
                .add(Attributes.ATTACK_DAMAGE, 6.0) // 攻撃力
                .add(Attributes.MOVEMENT_SPEED, 0.25); // 移動速度
    }

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
            return this.slime.level.getBlockState(this.slime.blockPosition().below()).is(Blocks.LAVA);
        }

        @Override
        public void start() {
            Vec3 avoidDirection = this.slime.getLookAngle().scale(-1);
            this.slime.getNavigation().moveTo(
                    this.slime.getX() + avoidDirection.x,
                    this.slime.getY() + avoidDirection.y,
                    this.slime.getZ() + avoidDirection.z,
                    speed
            );
        }
    }
}
