package com.Wadoo.hyperion.Server.Item;

import com.Wadoo.hyperion.Server.Entity.BasaltArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BasaltArrowItem extends ArrowItem {

    public BasaltArrowItem(Item.Properties properties) {
        super(properties);

    }

    public AbstractArrowEntity createArrow(World world, ItemStack itemStack, EntityType entity) {
        BasaltArrowEntity arrowentity = new BasaltArrowEntity(entity, world);
        return arrowentity;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == BasaltArrowItem.class;
    }

}
