package com.Wadoo.hyperion;


import com.Wadoo.hyperion.Server.Item.HyperionSpawnEggItem;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import com.Wadoo.hyperion.Server.Register.ItemRegister;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

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
        ItemRegister.ITEMS.register(bus);
        GeckoLib.initialize();
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        HyperionSpawnEggItem.RegisterSpawnEggs();
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

}
