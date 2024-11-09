package com.sakalti.elemenstars.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;

public class FireGreatSword extends SwordItem {

    public FireGreatSword() {
        super(new FireGreatSwordTier(), 20, -3.0F, new Item.Properties()); // 攻撃力を20、クールダウンを-3.0Fに設定
    }

    private static class FireGreatSwordTier implements Tier {
        @Override
        public int getUses() {
            return 1800; // 耐久値
        }

        @Override
        public float getSpeed() {
            return 8.0F; // 掘る速度
        }

        @Override
        public float getAttackDamage() {
            return 20.5F; // 攻撃力を20.5に設定
        }

        @Override
        public int getLevel() {
            return 3; // ティアレベル（ネザライトと同等）
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.BLAZE_ROD); // 修理素材
        }
    }
}
