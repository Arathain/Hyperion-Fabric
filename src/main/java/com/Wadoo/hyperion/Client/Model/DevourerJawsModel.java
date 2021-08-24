package com.Wadoo.hyperion.Client.Model;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.DevourerJawsItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DevourerJawsModel extends AnimatedGeoModel<DevourerJawsItem> {
@Override
public ResourceLocation getModelLocation(DevourerJawsItem object) {
        return new ResourceLocation(Hyperion.MOD_ID, "geo/items/devourer_jaws.json");
        }

@Override
public ResourceLocation getTextureLocation(DevourerJawsItem object) {
        return new ResourceLocation(Hyperion.MOD_ID, "textures/item/devourer_jaws.png");
        }

@Override
public ResourceLocation getAnimationFileLocation(DevourerJawsItem animatable) {
        return new ResourceLocation(Hyperion.MOD_ID, "animations/item/devourer_jaws/devourer_jaws.animations.json");
        }
}