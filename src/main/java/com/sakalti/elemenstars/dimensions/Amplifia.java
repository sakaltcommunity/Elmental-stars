package com.sakalti.elemenstars.dimension;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sakalti.elemenstars.Elemenstars;
import com.sakalti.elemenstars.entities.AmplifEntity;

public class Amplifia {

    public static final DeferredRegister<DimensionType> DIMENSIONS = DeferredRegister.create(ForgeRegistries.DIMENSIONS, Elemenstars.MOD_ID);
    public static final RegistryObject<DimensionType> AMPLIFIA_DIM = DIMENSIONS.register("amplifia",
        () -> DimensionType.createOverworld(new AmplifiaDimensionType()));

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Elemenstars.MOD_ID);
    public static final RegistryObject<Biome> HARSH_LAND = BIOMES.register("harsh_land", Amplifia::createAmplifiedBiome);

    public static void register(IEventBus eventBus) {
        DIMENSIONS.register(eventBus);
        BIOMES.register(eventBus);
        MinecraftForge.EVENT_BUS.addListener(Amplifia::setup);
    }

    private static void setup() {
        BiomeDictionary.addTypes(HARSH_LAND.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MOUNTAIN);
    }

    private static Biome createAmplifiedBiome() {
        return new Biome.BiomeBuilder()
            .generationSettings(new BiomeGenerationSettings.Builder()
                .surfaceBuilder(ConfiguredSurfaceBuilder.of(SurfaceBuilder.DEFAULT, SurfaceRuleData.OVERWORLD))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnEntry(AmplifEntity.AMPLIF.get(), 100, 2, 4))
                .build())
            .temperature(0.8F)
            .downfall(0.5F)
            .build();
    }
}
