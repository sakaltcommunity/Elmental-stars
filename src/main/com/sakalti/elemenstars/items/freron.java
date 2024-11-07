package com.sakalti.elemenstars.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.FoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class Freron extends Item {

    public Freron(Properties properties) {
        super(properties.food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        super.finishUsingItem(stack, level, entity);

        // プレイヤーが食べた後にHPを回復（5tick後に効果）
        if (entity instanceof Player player) {
            player.heal(2.0F); // HP2回復
        }
        return stack;
    }
}
