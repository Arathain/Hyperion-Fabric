package com.Wadoo.hyperion.client.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.server.entity.GruskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GruskModel extends AnimatedGeoModel<GruskEntity> {
    @Override
    public ResourceLocation getModelLocation(GruskEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_devourer/basalt_devourer.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GruskEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_devourer/basalt_devourer.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GruskEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_devourer/basalt_devourer.animations.json");
    }

}
