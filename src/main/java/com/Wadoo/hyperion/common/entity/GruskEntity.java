package com.Wadoo.hyperion.common.entity;

import com.Wadoo.hyperion.common.registry.HyperionSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GruskEntity extends HostileEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Boolean> ACTIVATED = DataTracker.registerData(GruskEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public GruskEntity(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createGruskAttributes() {
        return MobEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 35.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.3D)
                .add(EntityAttributes.GENERIC_ARMOR, 4D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.5D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.getTarget() != null && !this.getActivated()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.grusk.activate", true));
            return PlayState.CONTINUE;
        }
        if(!getActivated()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.grusk.idle_deactivated", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.grusk.walk", true));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.grusk.idle_activated", true));
            return PlayState.CONTINUE;
        }
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<GruskEntity>(this, "controller", 7, this::predicate));

    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, GruskEntity.class, 8.0F));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getTarget() == null){
            setActivated(false);
            this.getNavigation().stop();
            this.setVelocity(0d, this.getVelocity().y, 0D);
        }

    }


    @Override
    protected SoundEvent getDeathSound() {
        return HyperionSoundEvents.GRUSK_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return HyperionSoundEvents.GRUSK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return HyperionSoundEvents.GRUSK_HURT;
    }



    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ACTIVATED, false);
    }

    public void setActivated(boolean activated){
        this.dataTracker.set(ACTIVATED, activated);
    }

    public boolean getActivated(){
        return this.dataTracker.get(ACTIVATED);
    }
}
