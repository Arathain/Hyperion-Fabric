package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.BasaltCapslingModel;
import com.Wadoo.hyperion.Client.Renderer.Layers.BasaltItemLayer;
import com.Wadoo.hyperion.Server.Entity.BasaltCapslingEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BasaltCapslingRenderer extends GeoEntityRenderer<BasaltCapslingEntity> {
    public BasaltCapslingRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BasaltCapslingModel());
        this.addLayer(new BasaltItemLayer(this));
    }

    @Override
    public RenderType getRenderType(BasaltCapslingEntity animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

}