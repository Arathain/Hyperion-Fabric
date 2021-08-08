package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.WarpedFunglingModel;
import com.Wadoo.hyperion.Server.Entity.AI.WarpedFunglingEntity;
import com.Wadoo.hyperion.Server.Entity.BasaltCapslingEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WarpedFunglingRenderer extends GeoEntityRenderer<WarpedFunglingEntity> {
    public BasaltCapslingEntity entity;

    public WarpedFunglingRenderer(EntityRendererManager renderManager) {
        super(renderManager, new WarpedFunglingModel());
        this.shadowRadius = 0.2f;
    }

    @Override
    public RenderType getRenderType(WarpedFunglingEntity animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}