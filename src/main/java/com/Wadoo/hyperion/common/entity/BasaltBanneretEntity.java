package com.Wadoo.hyperion.common.entity;

import com.Wadoo.hyperion.common.entity.ai.BanneretAttackGoal;
import com.Wadoo.hyperion.common.entity.ai.BanneretStompGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasaltBanneretEntity extends HostileEntity implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Integer> ATTACK_STATE = DataTracker.registerData(BasaltBanneretEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public BasaltBanneretEntity(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createBanneretAttributes() {
        return MobEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 125.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.27001D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.3D)
                .add(EntityAttributes.GENERIC_ARMOR, 6D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.5D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.banneret.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder()
                .addAnimation("animation.banneret.idle", true));
        return PlayState.CONTINUE;
    }


    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACK_STATE, 0);
    }

    public int getAttackState() {
        return this.dataTracker.get(ATTACK_STATE);
    }

    public void setAttackState(int attackState){
        this.dataTracker.set(ATTACK_STATE, attackState);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BasaltBanneretEntity>(this, "controller", 10, this::predicate));
        //data.addAnimationController(new AnimationController<BasaltBanneretEntity>(this, "controllerAttack", 15, this::predicateAttack));
    }


    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, BasaltBanneretEntity.class, 8.0F));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, CapslingEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MagmaCubeEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, StriderEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, VillagerEntity.class, true));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(1, (new RevengeGoal(this, BasaltBanneretEntity.class)).setGroupRevenge());
        this.goalSelector.add(2, new BanneretStompGoal(this));
        this.goalSelector.add(2, new BanneretAttackGoal(this, 1.5D, true, 0, 20));

    }




    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void tick() {
        super.tick();
        //ItemTags.getAllTags().getTag(TagRegister.CAPSLING_WANTED).contains(itemstack.getItem())
    }

    @Override
    public int tickTimer() {
        return age;
    }
}
