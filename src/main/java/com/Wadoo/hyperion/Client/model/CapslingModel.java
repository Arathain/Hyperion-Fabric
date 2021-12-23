package com.Wadoo.hyperion.client.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.server.entity.CapslingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;



public class CapslingModel extends AnimatedTickingGeoModel<CapslingEntity> {

    @Override
    public ResourceLocation getModelLocation(CapslingEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_capsling.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CapslingEntity object) {
            return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_capsling.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CapslingEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_capsling.animations.json");
    }

    @Override
    public void setLivingAnimations(CapslingEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone Head = this.getAnimationProcessor().getBone("jaw");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        Head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        Head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
