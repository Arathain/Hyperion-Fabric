package com.Wadoo.hyperion.Client.Renderer;

import com.Wadoo.hyperion.Client.Model.BasaltCapslingModel;
import com.Wadoo.hyperion.Client.Renderer.Layers.BasaltItemLayer;
import com.Wadoo.hyperion.Server.Entity.BasaltCapslingEntity;
import com.Wadoo.hyperion.Server.Register.ItemRegister;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BasaltCapslingRenderer extends GeoEntityRenderer<BasaltCapslingEntity> {
    private static final ItemStack basalt = new ItemStack(Items.BASALT);


    public BasaltCapslingRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BasaltCapslingModel());
    }

    @Override
    public RenderType getRenderType(BasaltCapslingEntity animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void renderRecursively(GeoBone bone, MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if(bone.getName().equals("Item") && !bone.isHidden()){
            matrixStack.pushPose();
            matrixStack.translate(0.0D, 0.55D, 0);
            matrixStack.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderStatic(basalt, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, matrixStack, this.rtb);
            matrixStack.popPose();
        }
        super.renderRecursively(bone, matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }
}