package com.Wadoo.hyperion.common.registry;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.common.item.BasaltArrowItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class HyperionItems {
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static final Item PURE_BASALT = create("pure_basalt", new Item(new Item.Settings().group(Hyperion.HYPERION_GROUP).fireproof()));
    public static final Item BASALT_ARROW = create("basalt_arrow", new BasaltArrowItem(new Item.Settings().group(Hyperion.HYPERION_GROUP)));
    public static final Item CAPSLING_BUCKET = create("capsling_bucket", new EntityBucketItem(HyperionEntities.CAPSLING, Fluids.LAVA, SoundEvents.ITEM_BUCKET_FILL_LAVA, new Item.Settings().group(Hyperion.HYPERION_GROUP).fireproof().maxCount(1)));
    public static final Item CAPSLING_SPAWN_EGG = create("capsling_spawn_egg", new SpawnEggItem(HyperionEntities.CAPSLING, 0x868686, 0x3f3f4e, (new Item.Settings()).group(Hyperion.HYPERION_GROUP)));
    public static final Item GRUSK_SPAWN_EGG = create("grusk_spawn_egg", new SpawnEggItem(HyperionEntities.GRUSK, 0x696969, 0x1e2a37, (new Item.Settings()).group(Hyperion.HYPERION_GROUP)));



    private static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, new Identifier(Hyperion.MOD_ID, name));
        return item;
    }

    public static void init() {
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
    }
}