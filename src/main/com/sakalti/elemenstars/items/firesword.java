package com.sakalti.elemenstars.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;

public class FireSword extends SwordItem {

    public FireSword() {
        super(new FireSwordTier(), 12, -2.4F, new Properties()); // 攻撃力を12に設定
    }

    private static class FireSwordTier implements Tier {
        @Override
        public int getUses() {
            return 2000; // 耐久値
        }

        @Override
        public float getSpeed() {
            return 10.0F; // 掘る速度
        }

        @Override
        public float getAttackDamage() {
            return 12.0F; // 攻撃力を12に設定
        }

        @Override
        public int getLevel() {
            return 3; // ティアレベル（ネザライトと同じ）
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.BLAZE_ROD); // 修理素材
        }
    }
}
