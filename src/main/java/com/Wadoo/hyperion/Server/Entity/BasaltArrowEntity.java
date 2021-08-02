package com.Wadoo.hyperion.Server.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasaltArrowEntity extends AbstractArrowEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);


    public BasaltArrowEntity(EntityType<? extends AbstractArrowEntity> arrow, World world) {
        super(arrow, world);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.basalt_capsling.idle", true));
            return PlayState.CONTINUE;
    }

    @Override
    public void tick() {
        super.tick();
    }

    protected ItemStack getPickupItem() {
            return new ItemStack(Items.ARROW);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BasaltArrowEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
