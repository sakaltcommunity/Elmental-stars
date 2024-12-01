package com.sakalti.elemenstars.tabs;

import com.sakalti.elemenstars.elemenstars;
import com.sakalti.elemenstars.entities.IceSlimeEntity;
import com.sakalti.elemenstars.entities.IceZalgEntity;
import com.sakalti.elemenstars.items.IceArmorMaterial;
import com.sakalti.elemenstars.items.IceFlame;
import com.sakalti.elemenstars.items.IceIngot;
import net.minecraft.world.item.*;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = elemenstars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IceteriaTab {

    // DeferredRegisterでアイテム登録を管理
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, elemenstars.MOD_ID);

    // スポーンエッグ
    public static final RegistryObject<Item> ICE_SLIME_SPAWN_EGG = ITEMS.register("ice_slime_spawn_egg",
            () -> new SpawnEggItem(IceSlimeEntity.ICE_SLIME, 0xADD8E6, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> ICE_ZALG_SPAWN_EGG = ITEMS.register("ice_zalg_spawn_egg",
            () -> new SpawnEggItem(IceZalgEntity.ICE_ZALG, 0xADD8E6, 0x0000FF, new Item.Properties()));
    
    public static final RegistryObject<Item> ICEFLAME = ITEMS.register("iceflame", IceFlame::new);

    public static final RegistryObject<Item> ICE_INGOT = ITEMS.register("ice_ingot", IceIngot::new);
    // アイスアーマー
    public static final RegistryObject<Item> ICE_HELMET = ITEMS.register("ice_helmet",
            () -> new ArmorItem(new IceArmorMaterial(), ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> ICE_CHESTPLATE = ITEMS.register("ice_chestplate",
            () -> new ArmorItem(new IceArmorMaterial(), ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> ICE_LEGGINGS = ITEMS.register("ice_leggings",
            () -> new ArmorItem(new IceArmorMaterial(), ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> ICE_BOOTS = ITEMS.register("ice_boots",
            () -> new ArmorItem(new IceArmorMaterial(), ArmorItem.Type.BOOTS, new Item.Properties()));

    // 新しいクリエイティブモードタブを定義
    public static final CreativeModeTab ICETERIA_TAB = new CreativeModeTab("iceteria_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ICE_SLIME_SPAWN_EGG.get());
        }
    };

    // タブにアイテムを追加
    @SubscribeEvent
    public static void onCreativeTabBuild(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ICETERIA_TAB) {
            event.accept(ICE_SLIME_SPAWN_EGG);
            event.accept(ICE_ZALG_SPAWN_EGG);
            event.accept(ICE_HELMET);
            event.accept(ICE_CHESTPLATE);
            event.accept(ICE_LEGGINGS);
            event.accept(ICE_BOOTS);
            event.accept(ICEFLAME);
        }
    }
}
