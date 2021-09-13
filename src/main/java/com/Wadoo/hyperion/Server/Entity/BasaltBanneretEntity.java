package com.Wadoo.hyperion.Server.Entity;

import com.Wadoo.hyperion.Server.Entity.AI.BanneretAttackGoal;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
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

public class BasaltBanneretEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(BasaltBanneretEntity.class, EntityDataSerializers.INT);

    public BasaltBanneretEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);

    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving() || !(animationSpeed > -0.10F && animationSpeed < 0.10F)) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.banneret.walk", true));
            return PlayState.CONTINUE;

        }
        else{
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.banneret.idle", true));
            return PlayState.CONTINUE;

        }
    }

    private <E extends IAnimatable> PlayState predicateAttack(AnimationEvent<E> event) {
        if(getAttackState() == 1) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.banneret.attack", false));
            return PlayState.CONTINUE;

        }
        else{
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.banneret.idle", true));
            return PlayState.CONTINUE;

        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BasaltBanneretEntity>(this, "controller", 7, this::predicate));
        data.addAnimationController(new AnimationController<BasaltBanneretEntity>(this, "controllerAttack", 15, this::predicateAttack));

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, BasaltBanneretEntity.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, CapslingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MagmaCube.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Strider.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, BasaltBanneretEntity.class)).setAlertOthers());
        this.goalSelector.addGoal(2, new BanneretAttackGoal(this, 1.5D, true, 40, 40));

    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_STATE, 0);
    }

    public int getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    public void setAttackState(int attackState){
        this.entityData.set(ATTACK_STATE, attackState);
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
