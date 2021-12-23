package com.Wadoo.hyperion.server.world.generation;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.server.registry.EntityRegister;
import com.Wadoo.hyperion.server.utils.config.HyperionConfig;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HyperionEntitySpawns {
    public static void onBiomesLoad(BiomeLoadingEvent event) {
        if(event.getCategory() == Biome.BiomeCategory.NETHER) {
            if (event.getName().toString().equals("minecraft:basalt_deltas")) {
                event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(EntityRegister.CAPSLING.get(), HyperionConfig.BASALT_CAPSLING_WEIGHT.get(), 2, 3));
            }
        }
    }
}