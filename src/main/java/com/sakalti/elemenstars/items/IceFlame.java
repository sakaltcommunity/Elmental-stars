package com.sakalti.elemenstars.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;

public class IceFlame extends SwordItem {

    public IceFlame() {
        super(new IceTier(), 12, -2.4F, new Properties()); // 攻撃力を14に設定
    }

    private static class FireSwordTier implements Tier {
        @Override
        public int getUses() {
            return 2048; // 耐久値
        }

        @Override
        public float getSpeed() {
            return 12.0F; // 掘る速度
        }

        @Override
        public float getAttackDamage() {
            return 14.0F; // 攻撃力を12に設定
        }

        @Override
        public int getLevel() {
            return 5;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.FROST_INGOT); // 修理素材
        }
    }
}
