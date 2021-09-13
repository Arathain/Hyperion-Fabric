package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.Armour.DevourArmour;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


import ResourceLocation;

public class DevourArmourModel extends AnimatedGeoModel<DevourArmour> {
    @Override
    public ResourceLocation getModelLocation(DevourArmour object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/armour/devour_armour.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DevourArmour object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/models/armor/devour_armour_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DevourArmour animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/armour/devour_armour.animations.json");
    }
}
