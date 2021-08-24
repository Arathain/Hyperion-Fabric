package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.BasaltDevourerEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasaltDevourerModel extends AnimatedGeoModel<BasaltDevourerEntity> {
    @Override
    public ResourceLocation getModelLocation(BasaltDevourerEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_devourer/basalt_devourer.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasaltDevourerEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_devourer/basalt_devourer.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasaltDevourerEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_devourer/basalt_devourer.animations.json");
    }

}
