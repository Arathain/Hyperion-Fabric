package com.Wadoo.hyperion.client.renderer;

import com.Wadoo.hyperion.client.model.GruskModel;
import com.Wadoo.hyperion.common.entity.GruskEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GruskRenderer extends GeoEntityRenderer<GruskEntity> {
    public GruskEntity entity;

    public GruskRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GruskModel());
        this.shadowRadius = 0.6F;
    }
}