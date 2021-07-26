package com.Wadoo.hyperion.Client;

import com.Wadoo.hyperion.Client.Renderer.BasaltCapslingRenderer;
import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.BASALT_CAPSLING.get(),
                manager -> new BasaltCapslingRenderer(manager));
    }
}