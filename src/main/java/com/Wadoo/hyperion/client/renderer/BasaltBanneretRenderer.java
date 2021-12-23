package com.Wadoo.hyperion.client.renderer;

import com.Wadoo.hyperion.client.model.BasaltBanneretModel;
import com.Wadoo.hyperion.servers.entity.BasaltBanneretEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BasaltBanneretRenderer extends GeoEntityRenderer<BasaltBanneretEntity> {
    public BasaltBanneretEntity entity;

    public BasaltBanneretRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BasaltBanneretModel());
        this.shadowRadius = 0.6F;
    }

}