package com.Wadoo.hyperion.client;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.client.renderer.BasaltBanneretRenderer;
import com.Wadoo.hyperion.client.renderer.BasaltSpikeRenderer;
import com.Wadoo.hyperion.client.renderer.CapslingRenderer;
import com.Wadoo.hyperion.client.renderer.GruskRenderer;
import com.Wadoo.hyperion.server.item.HyperionSpawnEggItem;
import com.Wadoo.hyperion.server.register.EntityRegister;
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
    public static void registerRenderers(final FMLClientSetupEvent event) { }


    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegister.CAPSLING.get(), CapslingRenderer::new);
        event.registerEntityRenderer(EntityRegister.GRUSK.get(), GruskRenderer::new);
        event.registerEntityRenderer(EntityRegister.BASALT_BANNERET.get(), BasaltBanneretRenderer::new);
        event.registerEntityRenderer(EntityRegister.BASALT_SPIKE.get(), BasaltSpikeRenderer::new);

    }



    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        ItemColor eggColor = (stack, tintIndex) -> ((HyperionSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (HyperionSpawnEggItem e : HyperionSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}