package com.Wadoo.hyperion.server.registry;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.server.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegister {

    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Hyperion.MOD_ID);

    public static final RegistryObject<EntityType<CapslingEntity>> CAPSLING =
            ENTITIES.register("capsling",
                    ()->EntityType.Builder.of(CapslingEntity::new, MobCategory.CREATURE)
                            .fireImmune()
                            .sized(0.45F,1.0F)
                            .build("capsling"));

    public static final RegistryObject<EntityType<GruskEntity>> GRUSK =
            ENTITIES.register("basalt_devourer",
                    ()->EntityType.Builder.<GruskEntity>of(GruskEntity::new, MobCategory.MONSTER)
                            .sized(0.9F,1.5F)
                            .build("grusk"));

    public static final RegistryObject<EntityType<BasaltBanneretEntity>> BASALT_BANNERET =
            ENTITIES.register("basalt_banneret",
                    ()->EntityType.Builder.<BasaltBanneretEntity>of(BasaltBanneretEntity::new, MobCategory.MONSTER)
                            .sized(1.43F,4.1F)
                            .build("basalt_devourer"));


    public static final RegistryObject<EntityType<BasaltArrowEntity>> BASALT_ARROW =
            ENTITIES.register("basalt_arrow",
                    ()->EntityType.Builder.<BasaltArrowEntity>of(BasaltArrowEntity::new, MobCategory.MISC)
                            .sized(0.25F,0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .fireImmune()
                            .build("basalt_arrow"));

    public static final RegistryObject<EntityType<BasaltSpikeEntity>> BASALT_SPIKE =
            ENTITIES.register("basalt_spike",
                    ()->EntityType.Builder.<BasaltSpikeEntity>of(BasaltSpikeEntity::new, MobCategory.AMBIENT)
                            .sized(1.0F,1.0F)
                            .build("basalt_spike"));

}
