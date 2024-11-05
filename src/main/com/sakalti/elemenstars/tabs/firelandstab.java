package com.sakalti.elemenstars.tabs;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sakalti.elemenstars.ElemenStars; // MODのメインクラスをインポート

@Mod.EventBusSubscriber(modid = ElemenStars.MOD_ID)
public class FirelandTab {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElemenStars.MOD_ID);

    public static final RegistryObject<Item> NETHERRACK_ITEM = ITEMS.register("netherrack_item", () -> new Item(new Properties()));
    public static final RegistryObject<Item> STONE_ITEM = ITEMS.register("stone_item", () -> new Item(new Properties()));

    public static final Item[] items = {
        NETHERRACK_ITEM.get(),
        STONE_ITEM.get()
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
        }
    }
}
