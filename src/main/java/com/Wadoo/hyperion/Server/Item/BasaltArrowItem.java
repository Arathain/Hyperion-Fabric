package com.Wadoo.hyperion.Server.Item;

import com.Wadoo.hyperion.Server.Entity.BasaltArrowEntity;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BasaltArrowItem extends ArrowItem {

    public BasaltArrowItem(Item.Properties properties) {
        super(properties);
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new BasaltArrowEntity(EntityRegister.BASALT_ARROW.get(), shooter, worldIn);
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == BasaltArrowItem.class;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent("Travels shorter distances").setStyle(Style.EMPTY.withColor(Color.fromLegacyFormat(TextFormatting.GRAY)).withItalic(true)));
        tooltip.add(new StringTextComponent("Deals more damage the more armour your target has").setStyle(Style.EMPTY.withColor(Color.fromLegacyFormat(TextFormatting.GRAY)).withItalic(true)));
    }
}
