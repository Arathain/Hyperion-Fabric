package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.AI.WarpedFunglingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WarpedFunglingModel extends AnimatedGeoModel<WarpedFunglingEntity> {
    @Override
    public ResourceLocation getModelLocation(WarpedFunglingEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/warped_fungling/warped_fungling.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WarpedFunglingEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/warped_fungling/warped_fungling.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WarpedFunglingEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/warped_fungling/warped_fungling.animations.json");
    }
}
