package com.Wadoo.hyperion.Server.Register;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.BasaltCapslingEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegister {

    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Hyperion.MOD_ID);

    public static final RegistryObject<EntityType<BasaltCapslingEntity>> BASALT_CAPSLING =
            ENTITIES.register("basalt_capsling",
                    ()->EntityType.Builder.of(BasaltCapslingEntity::new, EntityClassification.CREATURE)
                            .fireImmune()
                            .sized(0.45F,1.0F)
                            .build("basalt_capsling"));

}
