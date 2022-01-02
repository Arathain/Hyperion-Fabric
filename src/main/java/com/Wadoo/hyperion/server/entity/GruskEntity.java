package com.Wadoo.hyperion.server.entity;

import com.Wadoo.hyperion.server.registry.SoundRegister;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GruskEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.defineId(GruskEntity.class, EntityDataSerializers.BOOLEAN);

    public GruskEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
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
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, GruskEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
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
            this.setDeltaMovement(0d, this.getDeltaMovement().y(), 0D);
        }

    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegister.GRUSK_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegister.GRUSK_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return SoundRegister.GRUSK_HURT.get();
    }



    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ACTIVATED, false);
    }

    public void setActivated(boolean activated){
        this.entityData.set(ACTIVATED, activated);
    }

    public boolean getActivated(){
        return this.entityData.get(ACTIVATED);
    }
}
