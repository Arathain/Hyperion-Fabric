package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.GruskEntity;
import net.minecraft.util.ResourceLocation;
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
