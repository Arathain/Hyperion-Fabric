package com.Wadoo.hyperion;

import com.Wadoo.hyperion.common.registry.HyperionEntities;
import com.Wadoo.hyperion.common.registry.HyperionItems;
import com.Wadoo.hyperion.common.registry.HyperionSoundEvents;
import com.Wadoo.hyperion.common.util.config.HyperionConfig;
import com.Wadoo.hyperion.common.world.generation.HyperionEntitySpawns;
import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

public class Hyperion implements ModInitializer {
	public static String MOD_ID = "hyperion";
	public static final ItemGroup HYPERION_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, MOD_ID), () -> new ItemStack(HyperionItems.PURE_BASALT));
	public static final HyperionConfig CONFIG = OmegaConfig.register(HyperionConfig.class);

	@Override
	public void onInitialize() {
		HyperionEntities.init();
		HyperionItems.init();
		HyperionSoundEvents.init();
		GeckoLib.initialize();
		HyperionEntitySpawns.init();
	}
}
