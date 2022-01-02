package com.Wadoo.hyperion;


import com.Wadoo.hyperion.server.item.HyperionSpawnEggItem;
import com.Wadoo.hyperion.server.registry.EntityRegister;
import com.Wadoo.hyperion.server.registry.ItemRegister;
import com.Wadoo.hyperion.server.registry.SoundRegister;
import com.Wadoo.hyperion.server.world.generation.HyperionEntitySpawns;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import javax.annotation.Nonnull;

// The value here should match an entry in the META-INF/mods.toml file

@Mod("hyperion")
public class Hyperion
{
    public static final String MOD_ID = "hyperion";
    private static final Logger LOGGER = LogManager.getLogger();
    public static final CreativeModeTab HYPERION_ITEM_GROUP = new CreativeModeTab("hyperion_tab") {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegister.PURE_BASALT.get());
        }
    };

    public Hyperion() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        EntityRegister.ENTITIES.register(bus);
        ItemRegister.ITEMS.register(bus);
        SoundRegister.SOUNDS.register(bus);
        MinecraftForge.EVENT_BUS.addListener(this::onBiomeLoad);
        GeckoLib.initialize();
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        HyperionSpawnEggItem.RegisterSpawnEggs();
    }

    public void onBiomeLoad(BiomeLoadingEvent event){
        HyperionEntitySpawns.onBiomesLoad(event);
    }
    
    private void setup(final FMLCommonSetupEvent event)
    {

    }

}
