package com.Wadoo.hyperion.client.render;

import com.Wadoo.hyperion.client.model.GruskModel;
import com.Wadoo.hyperion.server.entity.GruskEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GruskRenderer extends GeoEntityRenderer<GruskEntity> {
    public GruskEntity entity;

    public GruskRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GruskModel());
        this.shadowRadius = 0.6F;
    }
}