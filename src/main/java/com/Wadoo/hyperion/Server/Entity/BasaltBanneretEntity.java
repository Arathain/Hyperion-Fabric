package com.Wadoo.hyperion.Server.Entity;

import com.Wadoo.hyperion.Server.Entity.AI.BanneretAttackGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasaltBanneretEntity extends MonsterEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> ATTACK_STATE = EntityDataManager.defineId(BasaltBanneretEntity.class, DataSerializers.INT);

    public BasaltBanneretEntity(EntityType<? extends MonsterEntity> type, World world) {
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
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, BasaltBanneretEntity.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, CapslingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MagmaCubeEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, StriderEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
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
