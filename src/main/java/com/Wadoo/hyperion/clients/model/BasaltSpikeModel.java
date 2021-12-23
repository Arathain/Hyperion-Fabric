package com.Wadoo.hyperion.clients.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.server.entity.BasaltSpikeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class BasaltSpikeModel extends AnimatedGeoModel<BasaltSpikeEntity> {
    @Override
    public ResourceLocation getModelLocation(BasaltSpikeEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_spike.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasaltSpikeEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_spike.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasaltSpikeEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_spike.animations.json");
    }
}
