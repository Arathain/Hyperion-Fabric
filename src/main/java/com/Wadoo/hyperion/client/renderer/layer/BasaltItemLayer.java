package com.Wadoo.hyperion.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class BasaltItemLayer<T extends LivingEntity & IAnimatable, M extends EntityModel<T>> extends GeoLayerRenderer<T> {
    public BasaltItemLayer(IGeoRenderer<T> geoRenderer) {
        super(geoRenderer);
    }

    public void render(PoseStack matrixStack, MultiBufferSource p_225628_2_, int p_225628_3_, T p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.55D, 0);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
        matrixStack.scale(0.5F, 0.5F, 0.5F);
        ItemStack lvt_11_1_ = p_225628_4_.getItemBySlot(EquipmentSlot.MAINHAND);
        Minecraft.getInstance().getItemInHandRenderer().renderItem(p_225628_4_, lvt_11_1_, ItemTransforms.TransformType.GROUND, false, matrixStack, p_225628_2_, p_225628_3_);
        matrixStack.popPose();
        
    }

}


