package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.BasaltArrowEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasaltArrowModel extends AnimatedGeoModel<BasaltArrowEntity> {
    @Override
    public ResourceLocation getModelLocation(BasaltArrowEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_arrow/basalt_arrow.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasaltArrowEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_arrow/basalt_arrow.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasaltArrowEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_arrow/basalt_arrow.animations.json");
    }
}
