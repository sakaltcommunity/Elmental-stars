package com.sakalti.elemenstars.dimension;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sakalti.elemenstars.entities.IceSlimeEntity; // 新しいエンティティをインポート
import com.sakalti.elemenstars.elemenstars;

public class Iceteria {

    public static final DeferredRegister<DimensionType> DIMENSIONS = DeferredRegister.create(ForgeRegistries.DIMENSIONS, elemenstars.MOD_ID);
    public static final RegistryObject<DimensionType> ICETERIA_DIM = DIMENSIONS.register("iceteria", () -> DimensionType.createOverworld(new IceteriaDimensionType()));

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, elemenstars.MOD_ID);

    public static void register(IEventBus eventBus) {
        // ディメンションとバイオームをイベントバスに登録
        DIMENSIONS.register(eventBus);
        BIOMES.register(eventBus);

        // アイススライムエンティティのスポーン設定
        MinecraftForge.EVENT_BUS.addListener(this::setup);
    }

    private void setup() {
        // バイオーム設定
        Biome biome = new Biome.Builder()
            .generationSettings(new BiomeGenerationSettings.Builder()
                .surfaceBuilder(SurfaceBuilders.SNOWY)
                .feature(GenerationStep.Decoration.SURFACE_STRUCTURES, ConfiguredFeature.ICE_SPIKE)
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnEntry(IceSlimeEntity.ICE_SLIME.get(), 100, 2, 4))
                .build())
            .temperature(0.0F)  // 氷の温度
            .build();

        // アイステリアのバイオーム登録
        ForgeRegistries.BIOMES.register("iceteria", biome);
        BiomeDictionary.addTypes(biome, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.COLD);
    }
}
