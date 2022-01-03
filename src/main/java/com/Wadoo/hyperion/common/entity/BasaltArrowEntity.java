package com.Wadoo.hyperion.common.entity;

import com.Wadoo.hyperion.common.registry.HyperionEntities;
import com.Wadoo.hyperion.common.registry.HyperionItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasaltArrowEntity extends PersistentProjectileEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public LivingEntity shooter;
    public LivingEntity entity;

    public BasaltArrowEntity(EntityType<? extends BasaltArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.shooter = shooter;
        this.setDamage(3.7D);
    }

    public BasaltArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(HyperionEntities.BASALT_ARROW, world);
        this.setDamage(3.7D);
    }



    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(!this.isOnGround() && !this.inGround){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.basalt_arrow.inground", true));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.basalt_arrow.flying", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.setVelocity(this.getVelocity().subtract(0.0D, 0.06D, 0.0D));
    }


    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(HyperionItems.BASALT_ARROW);
    }

    protected ItemStack getPickupItem() {
        return new ItemStack((Item) HyperionItems.BASALT_ARROW);

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BasaltArrowEntity>(this, "controller", 2, this::predicate));
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        int arrowTimer = 50;
        this.entity = target;
        this.entity.damage(DamageSource.arrow(this, this.shooter), this.entity.getArmor() /2.0f * 0.09f);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
