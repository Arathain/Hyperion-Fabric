package com.Wadoo.hyperion.Server.Register;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.BasaltArrowEntity;
import com.Wadoo.hyperion.Server.Entity.BasaltCapslingEntity;
import com.Wadoo.hyperion.Server.Entity.WarpedFunglingEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
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
                            .build(new ResourceLocation(Hyperion.MOD_ID, "basalt_capsling").toString()));

    public static final RegistryObject<EntityType<WarpedFunglingEntity>> WARPED_FUNGLING =
            ENTITIES.register("warped_fungling",
                    ()->EntityType.Builder.of(WarpedFunglingEntity::new, EntityClassification.MONSTER)
                            .sized(0.5F,2.0F)
                            .build(new ResourceLocation(Hyperion.MOD_ID, "warped_fungling").toString()));

    public static final RegistryObject<EntityType<BasaltArrowEntity>> BASALT_ARROW =
            ENTITIES.register("basalt_arrow",
                    ()->EntityType.Builder.<BasaltArrowEntity>of(BasaltArrowEntity::new, EntityClassification.MISC)
                            .sized(0.25F,0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .fireImmune()
                            .build("basalt_arrow"));


}
