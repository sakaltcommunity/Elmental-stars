package com.sakalti.elemenstars.entities;

import com.sakalti.elemenstars.elemenstars;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = elemenstars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Nyewetum extends Monster {
    private static final int MAX_HEALTH = 700; // HP700

    // Nyewetumエンティティタイプ
    public static final EntityType<Nyewetum> NYEWETUM_TYPE = EntityType.Builder
            .<Nyewetum>of(Nyewetum::new, MobCategory.MONSTER)
            .sized(1.0F, 3.0F) // サイズ
            .build(elemenstars.MOD_ID + ":nyewetum");

    public Nyewetum(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setHealth(MAX_HEALTH);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 20, 15.0F)); // 遠距離攻撃
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 15.0F));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        SmallFireball fireball = new SmallFireball(this.level, this, target.getX() - this.getX(), target.getY() - this.getEyeY(), target.getZ() - this.getZ());
        fireball.setDeltaMovement(fireball.getDeltaMovement().scale(0.5)); // 弾速
        this.level.addFreshEntity(fireball);
        this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F); // 発射音
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        // 火の耐性
        return source.isFire() || super.isInvulnerableTo(source);
    }

    // レジストリイベントでNyewetumを登録
    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        NYEWETUM_TYPE.setRegistryName(elemenstars.MOD_ID, "nyewetum");
        event.getRegistry().register(NYEWETUM_TYPE);
    }
}
