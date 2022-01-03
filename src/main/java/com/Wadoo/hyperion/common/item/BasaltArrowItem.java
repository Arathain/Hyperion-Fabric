package com.Wadoo.hyperion.common.item;

import com.Wadoo.hyperion.common.entity.BasaltArrowEntity;
import com.Wadoo.hyperion.common.registry.HyperionEntities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BasaltArrowItem extends ArrowItem {

    public BasaltArrowItem(Item.Settings properties) {
        super(properties);
    }

    public PersistentProjectileEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new BasaltArrowEntity(HyperionEntities.BASALT_ARROW, shooter, worldIn);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new LiteralText("Travels shorter distances").setStyle(Style.EMPTY.withColor(Formatting.GRAY).withItalic(true)));
        tooltip.add(new LiteralText("Deals more damage the more armour your target has").setStyle(Style.EMPTY.withColor(Formatting.GRAY).withItalic(true)));
    }

}
