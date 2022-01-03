package com.Wadoo.hyperion.common.registry;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.common.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class HyperionEntities {

    private static final Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();


    public static final EntityType<CapslingEntity> CAPSLING = createEntity("capsling", CapslingEntity.createMobAttributes(), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CapslingEntity::new).dimensions(EntityDimensions.fixed(0.45F, 1.0F)).fireImmune().spawnGroup(SpawnGroup.CREATURE).build());
    public static final EntityType<GruskEntity> GRUSK = createEntity("grusk", GruskEntity.createMobAttributes(), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GruskEntity::new).dimensions(EntityDimensions.fixed(0.9F, 1.5F)).fireImmune().spawnGroup(SpawnGroup.MONSTER).build());
    public static final EntityType<BasaltBanneretEntity> BASALT_BANNERET = createEntity("basalt_banneret", BasaltBanneretEntity.createMobAttributes(), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BasaltBanneretEntity::new).dimensions(EntityDimensions.fixed(1.43F,4.1F)).fireImmune().spawnGroup(SpawnGroup.MONSTER).build());
    public static final EntityType<BasaltArrowEntity> BASALT_ARROW = createEntity("basalt_arrow", FabricEntityTypeBuilder.<BasaltArrowEntity>create(SpawnGroup.MISC, BasaltArrowEntity::new).dimensions(EntityType.ARROW.getDimensions()).fireImmune().trackRangeChunks(4).trackedUpdateRate(20).build());
    public static final EntityType<BasaltSpikeEntity> BASALT_SPIKE = createEntity("basalt_spike", FabricEntityTypeBuilder.<BasaltSpikeEntity>create(SpawnGroup.AMBIENT, BasaltSpikeEntity::new).dimensions(EntityDimensions.fixed(1.0F, 1.0F)).fireImmune().build());

    private static <T extends Entity> EntityType<T> createEntity(String name, EntityType<T> type) {
        ENTITY_TYPES.put(type, new Identifier(Hyperion.MOD_ID, name));
        return type;
    }

    private static <T extends LivingEntity> EntityType<T> createEntity(String name, DefaultAttributeContainer.Builder attributes, EntityType<T> type) {
        FabricDefaultAttributeRegistry.register(type, attributes);
        ENTITY_TYPES.put(type, new Identifier(Hyperion.MOD_ID, name));
        return type;
    }

    public static void init() {
        ENTITY_TYPES.keySet().forEach(entityType -> Registry.register(Registry.ENTITY_TYPE, ENTITY_TYPES.get(entityType), entityType));
    }
}
