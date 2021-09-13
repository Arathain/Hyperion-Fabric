package com.Wadoo.hyperion.Client;

import com.Wadoo.hyperion.Client.Renderer.BasaltBanneretRenderer;
import com.Wadoo.hyperion.Client.Renderer.CapslingRenderer;
import com.Wadoo.hyperion.Client.Renderer.GruskRenderer;
import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.HyperionSpawnEggItem;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {
    }


    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegister.CAPSLING.get(), CapslingRenderer::new);
        event.registerEntityRenderer(EntityRegister.GRUSK.get(), GruskRenderer::new);
        event.registerEntityRenderer(EntityRegister.BASALT_BANNERET.get(), BasaltBanneretRenderer::new);

    }



    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        ItemColor eggColor = (stack, tintIndex) -> ((HyperionSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (HyperionSpawnEggItem e : HyperionSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}