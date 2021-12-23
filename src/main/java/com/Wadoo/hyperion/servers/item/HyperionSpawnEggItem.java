package com.Wadoo.hyperion.servers.item;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("NullableProblems")
public class HyperionSpawnEggItem extends SpawnEggItem {
    public static final List<HyperionSpawnEggItem> UNADDED_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> entityTypeSupplier;
    private static final Map<EntityType<?>, SpawnEggItem> BY_ID = Maps.newIdentityHashMap();

    public HyperionSpawnEggItem(
            final NonNullSupplier<? extends EntityType<?>> entityTypeSupplier,
            int primaryColor,
            int secondaryColor,
            Properties builder
    ) {
        super(null, primaryColor, secondaryColor, builder);

        this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
        UNADDED_EGGS.add(this);
    }

    public static void RegisterSpawnEggs() {
        DefaultDispenseItemBehavior dispenserBehavior = new DefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());

                type.spawn(
                        source.getLevel(),
                        stack,
                        null,
                        source.getPos().relative(direction),
                        MobSpawnType.DISPENSER,
                        direction != Direction.UP,
                        false
                );
                stack.shrink(1);

                return stack;
            }
        };

        UNADDED_EGGS.forEach(spawnEgg -> {
            BY_ID.put(spawnEgg.getType(null), spawnEgg);
            DispenserBlock.registerBehavior(spawnEgg, dispenserBehavior);
        });

        UNADDED_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundTag nbt) {
        return this.entityTypeSupplier.get();
    }
}
