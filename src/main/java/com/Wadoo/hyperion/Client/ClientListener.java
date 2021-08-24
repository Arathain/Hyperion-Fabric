package com.Wadoo.hyperion.Client;

import com.Wadoo.hyperion.Client.Renderer.BasaltArrowRenderer;
import com.Wadoo.hyperion.Client.Renderer.BasaltCapslingRenderer;
import com.Wadoo.hyperion.Client.Renderer.BasaltDevourerRenderer;
import com.Wadoo.hyperion.Client.Renderer.DevourArmourRenderer;
import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.Armour.DevourArmourItem;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.BASALT_CAPSLING.get(),
                manager -> new BasaltCapslingRenderer(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.BASALT_ARROW.get(),
                manager -> new BasaltArrowRenderer(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.BASALT_DEVOURER.get(),
                manager -> new BasaltDevourerRenderer(manager));

        GeoArmorRenderer.registerArmorRenderer(DevourArmourItem.class, new DevourArmourRenderer());
    }
    
}