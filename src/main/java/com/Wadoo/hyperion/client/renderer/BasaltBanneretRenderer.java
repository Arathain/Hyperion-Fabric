package com.Wadoo.hyperion.client.renderer;

import com.Wadoo.hyperion.client.model.BasaltBanneretModel;
import com.Wadoo.hyperion.common.entity.BasaltBanneretEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BasaltBanneretRenderer extends GeoEntityRenderer<BasaltBanneretEntity> {
    public BasaltBanneretEntity entity;

    public BasaltBanneretRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BasaltBanneretModel());
        this.shadowRadius = 0.6F;
    }

}