package com.Wadoo.hyperion.client.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.server.entity.BasaltBanneretEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class BasaltBanneretModel extends AnimatedTickingGeoModel<BasaltBanneretEntity> {
    @Override
    public ResourceLocation getModelLocation(BasaltBanneretEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_banneret.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasaltBanneretEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_banneret.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasaltBanneretEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_banneret.animations.json");
    }

    @Override
    public void setLivingAnimations(BasaltBanneretEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
    }
}
