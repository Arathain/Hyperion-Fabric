package com.Wadoo.hyperion.client.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.common.entity.GruskEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GruskModel extends AnimatedGeoModel<GruskEntity> {
    @Override
    public Identifier getModelLocation(GruskEntity object) {
        return new Identifier(Hyperion.MOD_ID, "geo/entity/grusk.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GruskEntity object) {
        return new Identifier(Hyperion.MOD_ID, "textures/entity/grusk.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GruskEntity animatable) {
        return new Identifier(Hyperion.MOD_ID, "animations/entity/grusk.animations.json");
    }

}
