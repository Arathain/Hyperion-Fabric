package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.GruskModel;
import com.Wadoo.hyperion.Server.Entity.GruskEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GruskRenderer extends GeoEntityRenderer<GruskEntity> {
    public GruskEntity entity;

    public GruskRenderer(EntityRendererManager renderManager) {
        super(renderManager, new GruskModel());
        this.shadowRadius = 0.6F;
    }

    @Override
    public RenderType getRenderType(GruskEntity animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}