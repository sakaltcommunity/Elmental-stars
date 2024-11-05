package com.sakalti.elemenstars.tabs;

import com.sakalti.elemenstars.items.FireSword;
import com.sakalti.elemenstars.items.FireGreatSword; // FireGreatSwordをインポート
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sakalti.elemenstars.elemenstars; // MODのメインクラスをインポート

@Mod.EventBusSubscriber(modid = elemenstars.MOD_ID)
public class FirelandTab {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, elemenstars.MOD_ID);

    public static final RegistryObject<Item> NETHERRACK_ITEM = ITEMS.register("netherrack_item", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STONE_ITEM = ITEMS.register("stone_item", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_SWORD = ITEMS.register("fire_sword", FireSword::new);
    public static final RegistryObject<Item> FIRE_GREAT_SWORD = ITEMS.register("fire_great_sword", FireGreatSword::new); // FireGreatSwordを追加

    public static final Item[] items = {
        NETHERRACK_ITEM.get(),
        STONE_ITEM.get(),
        FIRE_SWORD.get(),
        FIRE_GREAT_SWORD.get() // FireGreatSwordを追加
    };

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        for (Item item : items) {
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void onCreativeTabBuild(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(NETHERRACK_ITEM);
            event.accept(STONE_ITEM);
        } else if (event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(FIRE_SWORD);
            event.accept(FIRE_GREAT_SWORD); // FireGreatSwordを追加
        }
    }
}
