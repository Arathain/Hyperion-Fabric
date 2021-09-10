package com.Wadoo.hyperion.Server.Entity;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EntityHandler {
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(EntityRegister.CAPSLING.get(), MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH,5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.278D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.3D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .build());

        event.put(EntityRegister.GRUSK.get(), MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH,35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.251D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.3D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.5D)
                .build());

        event.put(EntityRegister.BASALT_BANNERET.get(), MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH,125.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.18001D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.3D)
                .add(Attributes.ARMOR, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.5D)
                .build());
    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        EntitySpawnPlacementRegistry.register(EntityRegister.CAPSLING   .get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CapslingEntity::canSpawn);
    }

}