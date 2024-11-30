package com.sakalti.elemenstars.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sakalti.elemenstars.Elemenstars;

public class AmplifEntity extends Monster {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Elemenstars.MOD_ID);
    public static final RegistryObject<EntityType<AmplifEntity>> AMPLIF = ENTITIES.register("amplif",
        () -> EntityType.Builder.of(AmplifEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F) // プレイヤーサイズ
            .build("amplif"));

    public AmplifEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    // AIの設定
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    // アトリビュートの設定
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 76.0D) // HP
            .add(Attributes.ATTACK_DAMAGE, 9.0D) // 攻撃力
            .add(Attributes.MOVEMENT_SPEED, 0.29D); // 移動速度
    }
}
