package com.Wadoo.hyperion.Server.Register;

import com.Wadoo.hyperion.Client.Renderer.DevourerJawsRenderer;
import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Item.Armour.DevourArmourItem;
import com.Wadoo.hyperion.Server.Item.BasaltArrowItem;
import com.Wadoo.hyperion.Server.Item.DevourerJawsItem;
import com.Wadoo.hyperion.Server.Item.HyperionSpawnEggItem;
import com.Wadoo.hyperion.Server.Utils.Enums.HyperionArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
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
                    new BasaltArrowItem(new Item.Properties().rarity(Rarity.RARE).fireResistant().tab(Hyperion.HYPERION_ITEM_GROUP)));

    public static final RegistryObject<Item> DEVOUR_CHESTPLATE = ITEMS.register("devour_chestplate",
            () -> new DevourArmourItem(HyperionArmorMaterial.DEVOUR, EquipmentSlotType.CHEST));

    public static final RegistryObject<DevourerJawsItem> DEVOURER_JAWS =
            ITEMS.register("devourer_jaws", () ->
                    new DevourerJawsItem(new Item.Properties().setISTER(() -> DevourerJawsRenderer::new).rarity(Rarity.UNCOMMON).defaultDurability(200).fireResistant().tab(Hyperion.HYPERION_ITEM_GROUP)));

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
