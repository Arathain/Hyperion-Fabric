package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.BasaltBanneretModel;
import com.Wadoo.hyperion.Server.Entity.BasaltBanneretEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BasaltBanneretRenderer extends GeoEntityRenderer<BasaltBanneretEntity> {
    public BasaltBanneretEntity entity;

    public BasaltBanneretRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BasaltBanneretModel());
        this.shadowRadius = 0.6F;
    }

}