package com.Wadoo.hyperion.client.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.common.entity.BasaltSpikeEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class BasaltSpikeModel extends AnimatedGeoModel<BasaltSpikeEntity> {
    @Override
    public Identifier getModelLocation(BasaltSpikeEntity object) {
        return new Identifier(Hyperion.MOD_ID, "geo/entity/basalt_spike.geo.json");
    }

    @Override
    public Identifier getTextureLocation(BasaltSpikeEntity object) {
        return new Identifier(Hyperion.MOD_ID, "textures/entity/basalt_spike.png");
    }

    @Override
    public Identifier getAnimationFileLocation(BasaltSpikeEntity animatable) {
        return new Identifier(Hyperion.MOD_ID, "animations/entity/basalt_spike.animations.json");
    }
}
