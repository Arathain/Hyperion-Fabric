package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.BasaltBanneretEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class BasaltBanneretModel extends AnimatedGeoModel<BasaltBanneretEntity> {
    @Override
    public ResourceLocation getModelLocation(BasaltBanneretEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_banneret/basalt_banneret.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasaltBanneretEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_banneret/basalt_banneret.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasaltBanneretEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_banneret/basalt_banneret.animations.json");
    }

    @Override
    public void setLivingAnimations(BasaltBanneretEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone Head = this.getAnimationProcessor().getBone("Head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        Head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        Head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
