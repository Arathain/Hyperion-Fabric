package com.Wadoo.hyperion.common.world.generation;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.common.entity.CapslingEntity;
import com.Wadoo.hyperion.common.registry.HyperionEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;


public class HyperionEntitySpawns {
    public static void init() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS), SpawnGroup.MONSTER, HyperionEntities.CAPSLING, Hyperion.CONFIG.basaltCapslingWeight, Hyperion.CONFIG.basaltCapslingMin, Hyperion.CONFIG.basaltCapslingMax);
        initSpawnRestrictions();
    }
    private static void initSpawnRestrictions() {
        SpawnRestrictionAccessor.callRegister(HyperionEntities.CAPSLING, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CapslingEntity::canSpawn);
    }
}