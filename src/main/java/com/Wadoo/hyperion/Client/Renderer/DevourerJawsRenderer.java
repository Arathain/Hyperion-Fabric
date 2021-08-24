package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.DevourerJawsModel;
import com.Wadoo.hyperion.Server.Item.DevourerJawsItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DevourerJawsRenderer extends GeoItemRenderer<DevourerJawsItem> {
    public DevourerJawsRenderer() {
        super(new DevourerJawsModel());

    }

}
