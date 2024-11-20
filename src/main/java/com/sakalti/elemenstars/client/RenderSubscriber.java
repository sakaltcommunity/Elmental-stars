package com.sakalti.elemenstars.client;

import com.sakalti.elemenstars.entities.ZalgEntity;
import com.sakalti.elemenstars.client.renderer.ZalgRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.ModEventSubscriber;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@ModEventSubscriber(modid = "elemenstars", bus = ModEventSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderSubscriber {

    // エンティティレンダラーの登録
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(IEventBus eventBus) {
        // Zalgエンティティのレンダラー登録
        eventBus.addListener((event) -> {
            Minecraft.getInstance().getEntityRenderDispatcher().renderers.put(ZalgEntity.ZALG.get(), new ZalgRenderer(Minecraft.getInstance().getEntityRenderDispatcher()));
        });
    }
}
