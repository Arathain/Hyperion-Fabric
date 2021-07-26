package com.Wadoo.hyperion;


import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("hyperion")
public class Hyperion
{
    public static final String MOD_ID = "hyperion";
    private static final Logger LOGGER = LogManager.getLogger();

    public Hyperion() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        EntityRegister.ENTITIES.register(bus);
        GeckoLib.initialize();
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

}
