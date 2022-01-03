package com.Wadoo.hyperion.client.renderer;

import com.Wadoo.hyperion.client.model.BasaltSpikeModel;
import com.Wadoo.hyperion.common.entity.BasaltSpikeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class BasaltSpikeRenderer extends GeoProjectilesRenderer<BasaltSpikeEntity> {

    public BasaltSpikeRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BasaltSpikeModel());
    }

    @Override
    public void render(BasaltSpikeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        matrixStackIn.pop();
    }
}
