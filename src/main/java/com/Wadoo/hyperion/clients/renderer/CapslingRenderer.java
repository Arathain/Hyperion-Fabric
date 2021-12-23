package com.Wadoo.hyperion.clients.renderer;

import com.Wadoo.hyperion.clients.model.CapslingModel;
import com.Wadoo.hyperion.server.entity.CapslingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CapslingRenderer extends GeoEntityRenderer<CapslingEntity> {
    public CapslingEntity entity;

    public CapslingRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CapslingModel());
        this.shadowRadius = 0.16788F;
    }

    @Override
    public void renderEarly(CapslingEntity entity, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        this.entity = entity;
        super.renderEarly(entity, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("Item")) {
            stack.pushPose();
            stack.translate(0.0D, 0.55D, 0.0D);
            stack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            stack.scale(0.5f, 0.5f, 0.5f);
            //Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItemBySlot(EquipmentSlot.MAINHAND), ItemTransforms.TransformType.GROUND, packedLightIn, packedOverlayIn, stack, this.rtb);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}