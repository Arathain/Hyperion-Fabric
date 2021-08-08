package com.Wadoo.hyperion.Server.Register;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.BasaltArrowItem;
import com.Wadoo.hyperion.Server.Item.HyperionSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegister {

    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Hyperion.MOD_ID);

    public static final RegistryObject<Item> PURE_BASALT =
            ITEMS.register("pure_basalt", () ->
                    new Item(new Item.Properties().rarity(Rarity.RARE).tab(Hyperion.HYPERION_ITEM_GROUP)));

    public static final RegistryObject<BasaltArrowItem> BASALT_ARROW =
            ITEMS.register("basalt_arrow", () ->
                    new BasaltArrowItem(new Item.Properties().rarity(Rarity.RARE).tab(Hyperion.HYPERION_ITEM_GROUP)));

    public static final RegistryObject<Item> BASALT_CAPSLING_SPAWN_EGG = ITEMS.register(
            "basalt_capsling_spawn_egg",
            () -> new HyperionSpawnEggItem(
                    EntityRegister.BASALT_CAPSLING::get,
                    0x868686,
                    0x3f3f4e,
                   (new Item.Properties().tab(Hyperion.HYPERION_ITEM_GROUP))
            )
    );
}
