package com.Wadoo.hyperion.servers.entity;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.servers.registry.EntityRegister;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EntityHandler {
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(EntityRegister.CAPSLING.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.278D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.3D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .build());

        event.put(EntityRegister.GRUSK.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.251D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.3D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.5D)
                .build());

         event.put(EntityRegister.BASALT_BANNERET.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,125.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.21001D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.3D)
                .add(Attributes.ARMOR, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.5D)
                .build());

    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        SpawnPlacements.register(EntityRegister.CAPSLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CapslingEntity::canSpawn);
    }

}