package com.Wadoo.hyperion.server.item;

import com.Wadoo.hyperion.server.entity.BasaltArrowEntity;
import com.Wadoo.hyperion.server.register.EntityRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BasaltArrowItem extends ArrowItem {

    public BasaltArrowItem(Item.Properties properties) {
        super(properties);
    }

    public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
        return new BasaltArrowEntity(EntityRegister.BASALT_ARROW.get(), shooter, worldIn);
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == BasaltArrowItem.class;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TextComponent("Travels shorter distances").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(true)));
        tooltip.add(new TextComponent("Deals more damage the more armour your target has").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(true)));
    }


}
