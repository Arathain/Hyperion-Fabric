package com.Wadoo.hyperion.Server.Register;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Entity.BasaltCapslingEntity;
import com.Wadoo.hyperion.Server.Entity.EntityHandler;
import com.Wadoo.hyperion.Server.Item.BasaltArrowItem;
import com.Wadoo.hyperion.Server.Item.HyperionSpawnEggItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegister {

    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Hyperion.MOD_ID);

    public static final RegistryObject<Item> PURE_BASALT =
            ITEMS.register("hyperion:pure_basalt", () ->
                    new Item(new Item.Properties().rarity(Rarity.RARE).tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<BasaltArrowItem> BASALT_ARROW =
            ITEMS.register("hyperion:basalt_arrow", () ->
                    new BasaltArrowItem(new Item.Properties().rarity(Rarity.RARE).tab(ItemGroup.TAB_MISC)));
}
