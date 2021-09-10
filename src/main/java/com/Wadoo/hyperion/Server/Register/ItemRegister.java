package com.Wadoo.hyperion.Server.Register;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.BasaltArrowItem;
import com.Wadoo.hyperion.Server.Item.HyperionSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegister {

    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Hyperion.MOD_ID);

    public static final RegistryObject<Item> PURE_BASALT =
            ITEMS.register("pure_basalt", () ->
                    new Item(new Item.Properties().fireResistant().tab(Hyperion.HYPERION_ITEM_GROUP)));

    public static final RegistryObject<BasaltArrowItem> BASALT_ARROW =
            ITEMS.register("basalt_arrow", () ->
                    new BasaltArrowItem(new Item.Properties().fireResistant().tab(Hyperion.HYPERION_ITEM_GROUP)));

    public static final RegistryObject<Item> CAPSLING_SPAWN_EGG = ITEMS.register(
            "capsling_spawn_egg",
            () -> new HyperionSpawnEggItem(
                    EntityRegister.CAPSLING::get,
                    0x868686,
                    0x3f3f4e,
                    (new Item.Properties().tab(Hyperion.HYPERION_ITEM_GROUP))));

    public static final RegistryObject<Item> GRUSK_SPAWN_EGG = ITEMS.register(
            "grusk_spawn_egg",
            () -> new HyperionSpawnEggItem(
                    EntityRegister.GRUSK::get,
                    0x696969,
                    0x1e2a37,
                    (new Item.Properties().tab(Hyperion.HYPERION_ITEM_GROUP))));


}
