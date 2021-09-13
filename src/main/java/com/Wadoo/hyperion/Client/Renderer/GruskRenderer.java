package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.GruskModel;
import com.Wadoo.hyperion.Server.Entity.GruskEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GruskRenderer extends GeoEntityRenderer<GruskEntity> {
    public GruskEntity entity;

    public GruskRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GruskModel());
        this.shadowRadius = 0.6F;
    }
}