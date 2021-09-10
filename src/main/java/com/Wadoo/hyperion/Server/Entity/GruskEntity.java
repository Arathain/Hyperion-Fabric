package com.Wadoo.hyperion.Server.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GruskEntity extends MonsterEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public GruskEntity(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);

    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        LivingEntity target = this.getTarget();
        if(this.isAggressive()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.devourer.chase", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
                    event.getController().setAnimation(new AnimationBuilder()
                            .addAnimation("animation.devourer.walk", true));
                    return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.devourer.idle", true));
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
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, GruskEntity.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, CapslingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MagmaCubeEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, StriderEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, GruskEntity.class)).setAlertOthers());
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5D, true));
    }



    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
