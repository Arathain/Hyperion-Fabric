package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.WarpedFunglingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

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

    @Override
    public void setLivingAnimations(WarpedFunglingEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone Head = this.getAnimationProcessor().getBone("Head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        Head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        Head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
