package com.Wadoo.hyperion.Server.Utils.Enums;

import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Register.ItemRegister;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum HyperionArmorMaterial implements IArmorMaterial {
    DEVOUR(Hyperion.MOD_ID + ":basalt", 8, new int[] { 7, 9, 11, 7 }, 530, SoundEvents.ARMOR_EQUIP_GENERIC, 2.2f, 6.9F, () -> {
        return Ingredient.of(ItemRegister.PURE_BASALT.get());
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 16, 16, 16, 16 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockBackResitance;
    private final LazyValue<Ingredient> repairMaterial;

    private HyperionArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountIn,
                                  int enchantabilityIn, SoundEvent soundEventIn, float knockBackResistanceIn,
                                  float toughnessIn, Supplier<Ingredient> repairMaterialIn) {
        this.name = nameIn;
        this.maxDamageFactor = maxDamageFactorIn;
        this.damageReductionAmountArray = damageReductionAmountIn;
        this.enchantability = enchantabilityIn;
        this.soundEvent = soundEventIn;
        this.toughness = toughnessIn;
        this.knockBackResitance = knockBackResistanceIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotType) {
        return MAX_DAMAGE_ARRAY[slotType.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotType) {
        return this.damageReductionAmountArray[slotType.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public net.minecraft.util.SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockBackResitance;
    }
}