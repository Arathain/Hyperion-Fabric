package com.Wadoo.hyperion.client.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.common.entity.CapslingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;



public class CapslingModel extends AnimatedTickingGeoModel<CapslingEntity> {

    @Override
    public Identifier getModelLocation(CapslingEntity object) {
        return new Identifier(Hyperion.MOD_ID, "geo/entity/basalt_capsling.geo.json");
    }

    @Override
    public Identifier getTextureLocation(CapslingEntity object) {
            return new Identifier(Hyperion.MOD_ID, "textures/entity/basalt_capsling.png");
    }

    @Override
    public Identifier getAnimationFileLocation(CapslingEntity animatable) {
        return new Identifier(Hyperion.MOD_ID, "animations/entity/basalt_capsling.animations.json");
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
