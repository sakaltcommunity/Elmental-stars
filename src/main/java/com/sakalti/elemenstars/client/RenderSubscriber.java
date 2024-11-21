package com.sakalti.elemenstars.client;

import com.sakalti.elemenstars.client.renderer.IceSlimeRenderer;
import com.sakalti.elemenstars.client.renderer.ZalgRenderer;
import com.sakalti.elemenstars.entities.IceSlimeEntity;
import com.sakalti.elemenstars.entities.ZalgEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "elemenstars", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderSubscriber {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // IceSlime のレンダラー登録
        event.registerEntityRenderer(IceSlimeEntity.ICE_SLIME, IceSlimeRenderer::new);

        // Zalg のレンダラー登録（既存の ZalgRenderer を使用）
        event.registerEntityRenderer(ZalgEntity.ZALG, ZalgRenderer::new);
    }
}
