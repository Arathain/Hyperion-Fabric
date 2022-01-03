package com.Wadoo.hyperion.client.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.common.entity.BasaltArrowEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class BasaltArrowModel extends AnimatedGeoModel<BasaltArrowEntity> {
    @Override
    public Identifier getModelLocation(BasaltArrowEntity object) {
        return new Identifier(Hyperion.MOD_ID, "geo/entity/basalt_arrow.geo.json");
    }

    @Override
    public Identifier getTextureLocation(BasaltArrowEntity object) {
        return new Identifier(Hyperion.MOD_ID, "textures/entity/basalt_arrow.png");
    }

    @Override
    public Identifier getAnimationFileLocation(BasaltArrowEntity animatable) {
        return new Identifier(Hyperion.MOD_ID, "animations/entity/basalt_arrow.animations.json");
    }
}
