package com.sakalti.elemenstars.tabs;

import com.sakalti.elemenstars.items.FireSword;
import com.sakalti.elemenstars.items.FireGreatSword;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sakalti.elemenstars.elemenstars; // MODのメインクラスをインポート

@Mod.EventBusSubscriber(modid = elemenstars.MOD_ID)
public class FirelandTab {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, elemenstars.MOD_ID);
    
    public static final RegistryObject<Item> FIRE_SWORD = ITEMS.register("fire_sword", FireSword::new);
    public static final RegistryObject<Item> FIRE_GREAT_SWORD = ITEMS.register("fire_great_sword", FireGreatSword::new);
    
    // 新しいタブの登録
    public static final CreativeModeTab FIRELAND_TAB = new CreativeModeTab("fireland_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(FIRE_SWORD.get());
        }
    };

    @SubscribeEvent
    public static void onCreativeTabBuild(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == FIRELAND_TAB) {
            event.accept(FIRE_SWORD);
            event.accept(FIRE_GREAT_SWORD);
        }
    }
}
