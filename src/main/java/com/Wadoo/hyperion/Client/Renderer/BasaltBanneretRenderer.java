package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.BasaltBanneretModel;
import com.Wadoo.hyperion.Server.Entity.BasaltBanneretEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BasaltBanneretRenderer extends GeoEntityRenderer<BasaltBanneretEntity> {
    public BasaltBanneretEntity entity;

    public BasaltBanneretRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BasaltBanneretModel());
        this.shadowRadius = 0.6F;
    }

    @Override
    public RenderType getRenderType(BasaltBanneretEntity animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}