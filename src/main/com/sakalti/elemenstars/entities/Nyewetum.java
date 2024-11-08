package com.sakalti.elemenstars.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.sounds.SoundEvents;

public class Nyewetum extends Monster {
    private static final int MAX_HEALTH = 700; // HP700

    public Nyewetum(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setHealth(MAX_HEALTH);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 20, 15.0F)); // 遠距離攻撃のゴールを追加
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 15.0F));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        SmallFireball fireball = new SmallFireball(this.level, this, target.getX() - this.getX(), target.getY() - this.getEyeY(), target.getZ() - this.getZ());
        double speed = 0.5;
        fireball.setDeltaMovement(fireball.getDeltaMovement().scale(speed));
        this.level.addFreshEntity(fireball);

        this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F); // 発射音
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        // 特定の攻撃には耐性を持たせるなどのカスタムコードも追加可能
        return super.hurt(source, amount);
    }
}
