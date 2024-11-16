package com.sakalti.elemenstars.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class EliteIceSlimeEntity extends Monster {

    public static final EntityType<EliteIceSlimeEntity> ELITE_ICE_SLIME = EntityType.Builder.of(EliteIceSlimeEntity::new, MobCategory.MONSTER)
            .sized(0.8F, 0.8F) // 通常より少し大きめ
            .build("elite_ice_slime");

    public EliteIceSlimeEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.xpReward = 10; // 通常より多い経験値
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5D, true)); // 素早い近接攻撃
        this.goalSelector.addGoal(2, new SnowballAttackGoal(this)); // 雪玉を投げる攻撃
        this.goalSelector.addGoal(3, new TeleportToPlayerGoal(this)); // 一定距離でプレイヤーに瞬間移動
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public int getMaxHealth() {
        return 86; // HP
    }

    @Override
    public float getAttackDamage() {
        // 難易度ごとの攻撃力設定
        switch (this.level.getDifficulty()) {
            case EASY:
                return 5.0F;
            case NORMAL:
                return 8.0F;
            case HARD:
                return 12.0F;
            default:
                return 0.0F;
        }
    }

    // 雪玉を投げるAI
    static class SnowballAttackGoal extends net.minecraft.world.entity.ai.goal.Goal {
        private final EliteIceSlimeEntity slime;
        private int attackCooldown;

        public SnowballAttackGoal(EliteIceSlimeEntity slime) {
            this.slime = slime;
            this.attackCooldown = 0;
        }

        @Override
        public boolean canUse() {
            // プレイヤーが近くにいる場合発動
            Player nearestPlayer = this.slime.level.getNearestPlayer(this.slime, 10.0D);
            return nearestPlayer != null;
        }

        @Override
        public void tick() {
            if (attackCooldown > 0) {
                attackCooldown--;
                return;
            }

            Player nearestPlayer = this.slime.level.getNearestPlayer(this.slime, 10.0D);
            if (nearestPlayer != null) {
                Vec3 direction = nearestPlayer.position().subtract(this.slime.position()).normalize();
                this.slime.level.addFreshEntity(new net.minecraft.world.entity.projectile.Snowball(this.slime.level, this.slime));
                attackCooldown = 60; // クールダウン
            }
        }
    }

    // プレイヤーに一定距離で瞬間移動するAI
    static class TeleportToPlayerGoal extends net.minecraft.world.entity.ai.goal.Goal {
        private final EliteIceSlimeEntity slime;
        private final Random random = new Random();

        public TeleportToPlayerGoal(EliteIceSlimeEntity slime) {
            this.slime = slime;
        }

        @Override
        public boolean canUse() {
            // プレイヤーとの距離が7以上で発動
            Player nearestPlayer = this.slime.level.getNearestPlayer(this.slime, 20.0D);
            return nearestPlayer != null && nearestPlayer.distanceTo(this.slime) > 7.0D;
        }

        @Override
        public void start() {
            Player nearestPlayer = this.slime.level.getNearestPlayer(this.slime, 20.0D);
            if (nearestPlayer != null) {
                double teleportX = nearestPlayer.getX() + (random.nextDouble() - 0.5) * 4.0; // ランダムな位置
                double teleportY = nearestPlayer.getY();
                double teleportZ = nearestPlayer.getZ() + (random.nextDouble() - 0.5) * 4.0;
                this.slime.moveTo(teleportX, teleportY, teleportZ);
            }
        }
    }
}
