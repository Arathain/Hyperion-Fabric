package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.Armour.DevourArmourItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DevourArmourModel extends AnimatedGeoModel<DevourArmourItem> {
    @Override
    public ResourceLocation getModelLocation(DevourArmourItem object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/entity/DevourArmour.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DevourArmourItem object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/entity/devour_armour.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DevourArmourItem animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/entity/devour_armour.animations.json");
    }
}
