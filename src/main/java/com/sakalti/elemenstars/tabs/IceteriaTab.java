package com.sakalti.elemenstars.tabs;

import com.sakalti.elemenstars.elemenstars;
import com.sakalti.elemenstars.entities.IceSlimeEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
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

    // アイススライムのスポーンエッグを登録
    public static final RegistryObject<Item> ICE_SLIME_SPAWN_EGG = ITEMS.register("ice_slime_spawn_egg",
            () -> new SpawnEggItem(IceSlimeEntity.ICE_SLIME, 0xADD8E6, 0xFFFFFF, // 色設定 (薄い青と白)
                    new Item.Properties()));

    // 新しいクリエイティブモードタブを定義
    public static final CreativeModeTab ICETERIA_TAB = new CreativeModeTab("iceteria_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ICE_SLIME_SPAWN_EGG.get()); // スポーンエッグをアイコンに設定
        }
    };

    // タブにアイテムを追加
    @SubscribeEvent
    public static void onCreativeTabBuild(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ICETERIA_TAB) {
            event.accept(ICE_SLIME_SPAWN_EGG); // スポーンエッグをタブに追加
        }
    }
}
