package com.sakalti.elemenstars;

import com.sakalti.elemenstars.tabs.FirelandTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("elemenstars")
public class elemenstars {
  
    public static final String MOD_ID = "elemenstars"; 

    public elemenstars() {
        FirelandTab.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        // 通常のセットアップ処理
        System.out.println("共通のセットアップが完了しました。"); // 日本語メッセージ
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        // クライアント専用のセットアップ処理
        System.out.println("クライアントのセットアップが完了しました。"); // 日本語メッセージ
    }
}
