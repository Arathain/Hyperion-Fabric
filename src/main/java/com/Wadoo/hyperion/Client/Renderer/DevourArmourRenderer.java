package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.DevourArmourModel;
import com.Wadoo.hyperion.Server.Item.Armour.DevourArmourItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class DevourArmourRenderer extends GeoArmorRenderer<DevourArmourItem>
{
    public DevourArmourRenderer()
    {
        super(new DevourArmourModel());

        //These values are what each bone name is in blockbench. So if your head bone is named "bone545",
        // make sure to do this.headBone = "bone545";

        // The default values are the ones that come with the default armor template in the geckolib blockbench plugin.
        this.headBone = "bipedHead";
        this.bodyBone = "bipedBody";
        this.rightArmBone = "bipedRightArm";
        this.leftArmBone = "bipedLeftArm";
        this.rightLegBone = "bipedRightLeg";
        this.leftLegBone = "bipedLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}