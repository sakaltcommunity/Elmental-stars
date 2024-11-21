package com.sakalti.elemenstars.entities;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomTeleportGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WanderAroundGoal;
import net.minecraft.world.entity.ai.goal.FlyingGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobType;
import net.minecraft.network.chat.TextComponent;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import com.sakalti.elemenstars.elemenstars;

import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.entity.boss.BossBar;
import net.minecraft.world.entity.boss.BossBarColor;
import net.minecraft.world.entity.boss.BossBarStyle;

public class IceZalgEntity extends Monster {

    public static final EntityType<IceZalgEntity> ICE_ZALG = EntityType.Builder.of(IceZalgEntity::new, MobCategory.MONSTER)
            .sized(1.4f, 2.9f)  // サイズ設定
            .build("ice_zalg");

    private BossBar bossBar;

    public IceZalgEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        this.xpReward = 999;  // 経験値設定
    }

    // AI：攻撃、ランダムテレポート、その他行動
    @Override
    protected void addGoals() {
        // 近接攻撃の設定
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));

        // ランダムテレポートの設定
        this.goalSelector.addGoal(2, new RandomTeleportGoal(this, 15.0, 10));

        // プレイヤーを見る
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));

        // 周囲を歩き回る
        this.goalSelector.addGoal(4, new WanderAroundGoal(this, 1.0D));

        // 空を飛ぶ
        this.goalSelector.addGoal(5, new FlyingGoal(this, 0.5D)); // 飛行の追加
    }

    // 属性設定：HP、ダメージ、移動速度など
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1000)  // HP設定
                .add(Attributes.ATTACK_DAMAGE, 10)  // 攻撃ダメージ設定
                .add(Attributes.FOLLOW_RANGE, 35.0D)  // フォロー範囲
                .add(Attributes.MOVEMENT_SPEED, 0.3D)  // 移動速度設定
                .add(Attributes.FLYING_SPEED, 0.4D); // 飛行速度を設定
    }

    // テレポートの処理：ランダムでテレポートを行う
    private static class RandomTeleportGoal extends Goal {
        private final IceZalgEntity entity;
        private final double range;
        private final int delay;

        public RandomTeleportGoal(IceZalgEntity entity, double range, int delay) {
            this.entity = entity;
            this.range = range;
            this.delay = delay;
        }

        @Override
        public boolean canUse() {
            // テレポート条件（ランダムでテレポートする）
            return this.entity.tickCount % this.delay == 0;
        }

        @Override
        public void start() {
            // ランダムに位置を決めてテレポートする
            double x = this.entity.getX() + (Math.random() * 2 - 1) * range;
            double y = this.entity.getY();
            double z = this.entity.getZ() + (Math.random() * 2 - 1) * range;
            this.entity.teleportTo(x, y, z);
        }
    }

    // 死亡時のカスタムドロップ
    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource, boolean wasRecentlyHit) {
        super.dropCustomDeathLoot(damageSource, wasRecentlyHit);
        // ここにカスタムドロップアイテムを追加
        this.spawnAtLocation(new ItemStack(Items.DIAMOND), 1.0F);
    }

    // ボスバーの作成
    private void createBossBar() {
        this.bossBar = new BossBar(
                new TextComponent("Ice Zalg"),  // ボスの名前
                BossBarColor.BLUE,  // ボスバーの色
                BossBarStyle.SOLID  // スタイル
        );
        this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());  // HPの進行度
    }

    // ボスバーの更新（毎ティックごとに呼ばれる）
    @Override
    public void aiStep() {
        super.aiStep();
        if (this.bossBar != null) {
            this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());  // HPの進行度を更新
        }
    }

    // スポーン時の設定
    @Override
    public void onSpawn() {
        super.onSpawn();
        this.createBossBar();  // ボスバーを作成
    }

    public static void register(IEventBus eventBus) {
        RegistryObject<EntityType<IceZalgEntity>> entity = RegistryObject.of(ICE_ZALG, ForgeRegistries.ENTITY_TYPES);
        eventBus.addListener(() -> {
            EntityType.Builder<IceZalgEntity> builder = EntityType.Builder.of(IceZalgEntity::new, MobCategory.MONSTER);
            builder.sized(1.4f, 2.9f);  // サイズ設定
            builder.setTrackingRange(80);
            builder.setUpdateInterval(3);
            builder.setShouldReceiveVelocityUpdates(true);
        });
    }
}
