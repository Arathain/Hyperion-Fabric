package com.Wadoo.hyperion.Server.Entity;

import com.Wadoo.hyperion.Server.Register.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasaltArrowEntity extends AbstractArrowEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public LivingEntity shooter;
    public LivingEntity entity;

    public BasaltArrowEntity(EntityType<? extends BasaltArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.shooter = shooter;
        this.setBaseDamage(3.7D);
    }

    public BasaltArrowEntity(EntityType<BasaltArrowEntity> type, World world) {
        super(type, world);
        this.setBaseDamage(3.7D);
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
        this.setDeltaMovement(this.getDeltaMovement().subtract(0.0D, 0.06D, 0.0D));
    }




    protected ItemStack getPickupItem() {
            return new ItemStack(ItemRegister.BASALT_ARROW.get());
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BasaltArrowEntity>(this, "controller", 2, this::predicate));
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        int arrowTimer = 50;
        this.entity = entity;
        this.entity.hurt(DamageSource.arrow(this, this.shooter), (this.entity.getArmorValue() /2.0f) * 0.09f);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
