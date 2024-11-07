package com.sakalti.elemenstars.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

public class Hiver extends Item {
    private static final double DAMAGE = 2.88; // ダメージ量
    private static final int COOLDOWN_TICKS = 3; // クールダウン（3tick）
    private static final int MAX_USE_DISTANCE = 15; // 攻撃可能な距離
    private static final int MAX_DURABILITY = 4900; // 耐久値

    public Hiver(Properties properties) {
        super(properties.durability(MAX_DURABILITY));
    }

    @Override
    public InteractionResult useOnEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!player.level.isClientSide) {
            Level level = player.level;

            // クールダウンがある場合、使用を無効にする
            ItemCooldowns cooldowns = player.getCooldowns();
            if (cooldowns.isOnCooldown(this)) {
                return InteractionResult.PASS;
            }

            // エンティティの距離を確認
            Vec3 playerPosition = player.getEyePosition(1.0F);
            Vec3 lookDirection = player.getLookAngle().scale(MAX_USE_DISTANCE);
            Vec3 reachEnd = playerPosition.add(lookDirection);
            HitResult hitResult = level.clip(new net.minecraft.world.phys.BlockHitResult(reachEnd));

            if (hitResult != null && hitResult.getType() == HitResult.Type.ENTITY && hitResult.distanceTo(player) <= MAX_USE_DISTANCE) {
                // 攻撃を行う
                target.hurt(DamageSource.playerAttack(player), (float) DAMAGE);
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));

                // クールダウンの設定
                cooldowns.addCooldown(this, COOLDOWN_TICKS);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
