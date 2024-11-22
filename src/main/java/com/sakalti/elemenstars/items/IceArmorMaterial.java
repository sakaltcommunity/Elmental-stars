package com.sakalti.elemenstars.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ArmorItem.Type;

public class IceArmorMaterial implements ArmorMaterial {

    private static final int[] DURABILITY_PER_SLOT = new int[]{821, 1075, 789, 800}; // 頭、胸、脚、足
    private static final int[] DEFENSE_PER_SLOT = new int[]{4, 9, 7, 4}; // 防御力
    private static final int ENCHANTABILITY = 10; // エンチャント性
    private static final float TOUGHNESS = 4.0F; // 耐久性
    private static final float KNOCKBACK_RESISTANCE = 0.7F; // ノックバック耐性

    @Override
    public int getDurabilityForType(Type type) {
        return DURABILITY_PER_SLOT[type.ordinal()];
    }

    @Override
    public int getDefenseForType(Type type) {
        return DEFENSE_PER_SLOT[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return ENCHANTABILITY;
    }

    @Override
    public net.minecraft.sounds.SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_DIAMOND; // 装備音
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Items.DIAMOND); // 修理素材
    }

    @Override
    public String getName() {
        return "ice_armor"; // テクスチャパスに使用
    }

    @Override
    public float getToughness() {
        return TOUGHNESS;
    }

    @Override
    public float getKnockbackResistance() {
        return KNOCKBACK_RESISTANCE;
    }
}
