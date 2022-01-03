package com.Wadoo.hyperion.client.renderer;

import com.Wadoo.hyperion.client.model.CapslingModel;
import com.Wadoo.hyperion.common.entity.CapslingEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.Vec3f;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CapslingRenderer extends GeoEntityRenderer<CapslingEntity> {
    public CapslingEntity entity;
    private VertexConsumerProvider vertexConsumerProvider;

    public CapslingRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CapslingModel());
        this.shadowRadius = 0.16788F;
    }

    @Override
    public void renderEarly(CapslingEntity animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        this.entity = animatable;
        this.vertexConsumerProvider = renderTypeBuffer;
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

    @Override
    public void renderRecursively(GeoBone bone, MatrixStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("Item")) {
            stack.push();
            stack.translate(0.0D, 0.55D, 0.0D);
            stack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180.0F));
            stack.scale(0.5f, 0.5f, 0.5f);
            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getEquippedStack(EquipmentSlot.MAINHAND), ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, vertexConsumerProvider, 0);
            stack.pop();
            bufferIn = rtb.getBuffer(RenderLayer.getEntityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

}