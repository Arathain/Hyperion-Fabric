package com.Wadoo.hyperion.Server.World.Generation;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import com.Wadoo.hyperion.Server.Utils.Config.HyperionConfig;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HyperionEntitySpawns {
    public static void onBiomesLoad(BiomeLoadingEvent event) {
        System.out.println(event.getName());
                if(event.getName().toString().equals("minecraft:basalt_deltas")){
                    event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityRegister.CAPSLING.get(), HyperionConfig.BASALT_CAPSLING_WEIGHT.get(), HyperionConfig.BASALT_CAPSLING_MIN.get(), HyperionConfig.BASALT_CAPSLING_MAX.get()));
                }
    }
}