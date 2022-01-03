package com.Wadoo.hyperion;

import com.Wadoo.hyperion.client.renderer.*;
import com.Wadoo.hyperion.common.registry.HyperionEntities;
import draylar.omegaconfiggui.OmegaConfigGui;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class HyperionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        OmegaConfigGui.registerConfigScreen(Hyperion.CONFIG);
        EntityRendererRegistry.INSTANCE.register(HyperionEntities.CAPSLING, CapslingRenderer::new);
        EntityRendererRegistry.INSTANCE.register(HyperionEntities.GRUSK, GruskRenderer::new);
        EntityRendererRegistry.INSTANCE.register(HyperionEntities.BASALT_BANNERET, BasaltBanneretRenderer::new);
        EntityRendererRegistry.INSTANCE.register(HyperionEntities.BASALT_SPIKE, BasaltSpikeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(HyperionEntities.BASALT_ARROW, BasaltArrowRenderer::new);
    }
}
