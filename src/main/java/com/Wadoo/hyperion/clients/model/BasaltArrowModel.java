package com.Wadoo.hyperion.clients.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.server.entity.BasaltArrowEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class BasaltArrowModel extends AnimatedGeoModel<BasaltArrowEntity> {
    @Override
    public ResourceLocation getModelLocation(BasaltArrowEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_arrow.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasaltArrowEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_arrow.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasaltArrowEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_arrow.animations.json");
    }
}
