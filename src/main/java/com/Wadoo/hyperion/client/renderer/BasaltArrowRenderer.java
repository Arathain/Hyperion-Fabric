package com.Wadoo.hyperion.client.renderer;

import com.Wadoo.hyperion.client.model.BasaltArrowModel;
import com.Wadoo.hyperion.common.entity.BasaltArrowEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class BasaltArrowRenderer extends GeoProjectilesRenderer<BasaltArrowEntity> {

    public BasaltArrowRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BasaltArrowModel());
    }

    @Override
    public void render(BasaltArrowEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        matrixStackIn.pop();
    }

}
