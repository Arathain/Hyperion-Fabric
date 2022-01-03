package com.Wadoo.hyperion.client.renderer.layer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@Environment(EnvType.CLIENT)
public class BasaltItemLayer<T extends LivingEntity & IAnimatable, M extends EntityModel<T>> extends GeoLayerRenderer<T> {
    public BasaltItemLayer(IGeoRenderer<T> geoRenderer) {
        super(geoRenderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStackIn.push();
        matrixStackIn.translate(0.0D, 0.55D, 0);
        matrixStackIn.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180.0F));
        matrixStackIn.scale(0.5F, 0.5F, 0.5F);
        ItemStack stack = entitylivingbaseIn.getEquippedStack(EquipmentSlot.MAINHAND);
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedLightIn, matrixStackIn, bufferIn, 0);
        matrixStackIn.pop();
    }


}


