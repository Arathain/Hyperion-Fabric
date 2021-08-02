package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.BasaltArrowModel;
import com.Wadoo.hyperion.Server.Entity.BasaltArrowEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

import javax.annotation.Nullable;

public class BasaltArrowRenderer extends GeoProjectilesRenderer<BasaltArrowEntity> {

    public BasaltArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BasaltArrowModel());
    }

    @Override
    public void renderEarly(BasaltArrowEntity animatable, MatrixStack stackIn, float ticks, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }
}
