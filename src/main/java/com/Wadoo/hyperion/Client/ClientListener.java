package com.Wadoo.hyperion.Client;

import com.Wadoo.hyperion.Client.Renderer.BasaltArrowRenderer;
import com.Wadoo.hyperion.Client.Renderer.BasaltCapslingRenderer;
import com.Wadoo.hyperion.Client.Renderer.BasaltDevourerRenderer;
import com.Wadoo.hyperion.Client.Renderer.DevourArmourRenderer;
import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.Armour.DevourArmour;
import com.Wadoo.hyperion.Server.Item.HyperionSpawnEggItem;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.BASALT_CAPSLING.get(),
                manager -> new BasaltCapslingRenderer(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.BASALT_ARROW.get(),
                manager -> new BasaltArrowRenderer(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.BASALT_DEVOURER.get(),
                manager -> new BasaltDevourerRenderer(manager));

        GeoArmorRenderer.registerArmorRenderer(DevourArmour.class, new DevourArmourRenderer());
    }

    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((HyperionSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (HyperionSpawnEggItem e : HyperionSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}