package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.BasaltCapslingEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BasaltCapslingModel extends AnimatedGeoModel<BasaltCapslingEntity> {

    @Override
    public ResourceLocation getModelLocation(BasaltCapslingEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_capsling/basalt_capsling.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasaltCapslingEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_capsling/basalt_capsling.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasaltCapslingEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/basalt_capsling/basalt_capsling.animations.json");
    }

}
