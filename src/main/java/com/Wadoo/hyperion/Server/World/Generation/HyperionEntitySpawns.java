package com.Wadoo.hyperion.Server.World.Generation;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HyperionEntitySpawns {
    public static void onBiomesLoad(BiomeLoadingEvent event) {
            if (event.getCategory() == Biome.Category.NETHER) {
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(EntityRegister.BASALT_CAPSLING.get(), 70, 25, 26));
            }
    }
}