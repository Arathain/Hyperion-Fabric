package com.Wadoo.hyperion.Server.Entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GruskEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public GruskEntity(EntityType<? extends Monster> type, Level world) {
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
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, GruskEntity.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, CapslingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MagmaCube.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Strider.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
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
