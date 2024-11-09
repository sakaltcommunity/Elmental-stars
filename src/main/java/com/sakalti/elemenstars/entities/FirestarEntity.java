package com.sakalti.elemenstars.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumSet;
import java.util.Random;

public class FireStarEntity extends Monster {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, "elemenstars");

    public static final RegistryObject<EntityType<FireStarEntity>> FIRE_STAR = ENTITIES.register("fire_star", 
        () -> EntityType.Builder.of(FireStarEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F) // サイズ設定
            .build("fire_star")
    );

    public FireStarEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setHealth(45); // HPを45に設定
    }

    // AIの設定
    @Override
    protected void addBehaviourGoals() {
        // 攻撃するためのAI
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        // 近づいて攻撃するAIを設定
        this.goalSelector.addGoal(2, new FireStarAttackGoal(this));
    }

    // エンティティがダメージを受けた時の処理
    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        if (!this.level.isClientSide) {
            Random random = new Random();
            if (random.nextFloat() < 0.15) { // 15%の確率でドロップ
                ItemStack drop = new ItemStack(Items.BLAZE_POWDER); // ブレイズパウダーを設定
                this.spawnAtLocation(drop, 0.0F); // ドロップアイテムをスポーン
            }
        }
    }

    // ダメージの設定（4ダメージ）
    @Override
    public boolean doHurtTarget(Entity target) {
        if (target instanceof Player) {
            target.hurt(DamageSource.mobAttack(this), 4); // ダメージ4
            return true;
        }
        return super.doHurtTarget(target);
    }

    // エンティティ名を設定
    @Override
    public void setCustomName(Component name) {
        super.setCustomName(new TextComponent("FireStar"));
    }

    // AIの補助メソッド
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 45.0) // HP
                .add(Attributes.ATTACK_DAMAGE, 4.0) // 攻撃力
                .add(Attributes.MOVEMENT_SPEED, 0.25); // 移動速度
    }

    // 攻撃行動を追加
    static class FireStarAttackGoal extends Goal {
        private final FireStarEntity fireStar;
        
        public FireStarAttackGoal(FireStarEntity fireStar) {
            this.fireStar = fireStar;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return fireStar.getTarget() != null && fireStar.getTarget().isAlive();
        }

        @Override
        public void tick() {
            Entity target = fireStar.getTarget();
            if (target != null) {
                fireStar.getLookControl().setLookAt(target, 30.0F, 30.0F);
                fireStar.getNavigation().moveTo(target, 1.0D);
            }
        }
    }
}
