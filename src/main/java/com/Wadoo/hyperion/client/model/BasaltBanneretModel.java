package com.Wadoo.hyperion.client.model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.common.entity.BasaltBanneretEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class BasaltBanneretModel extends AnimatedTickingGeoModel<BasaltBanneretEntity> {
    @Override
    public Identifier getModelLocation(BasaltBanneretEntity object) {
        return new Identifier(Hyperion.MOD_ID, "geo/entity/banneret.geo.json");
    }

    @Override
    public Identifier getTextureLocation(BasaltBanneretEntity object) {
        return new Identifier(Hyperion.MOD_ID, "textures/entity/banneret.png");
    }

    @Override
    public Identifier getAnimationFileLocation(BasaltBanneretEntity animatable) {
        return new Identifier(Hyperion.MOD_ID, "animations/entity/banneret.animations.json");
    }

    @Override
    public void setLivingAnimations(BasaltBanneretEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
    }
}
