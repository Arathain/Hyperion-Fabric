package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.CapslingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CapslingModel extends AnimatedGeoModel<CapslingEntity> {

    @Override
    public ResourceLocation getModelLocation(CapslingEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/basalt_capsling/basalt_capsling.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CapslingEntity object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/basalt_capsling/basalt_capsling.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CapslingEntity animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/basalt_capsling/basalt_capsling.animations.json");
    }

    @Override
    public void setLivingAnimations(CapslingEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone Head = this.getAnimationProcessor().getBone("Jaw");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        Head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        Head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
