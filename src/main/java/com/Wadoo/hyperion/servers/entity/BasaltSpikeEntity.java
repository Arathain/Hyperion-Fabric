package com.Wadoo.hyperion.servers.entity;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasaltSpikeEntity extends Entity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Boolean> SUMMONED = SynchedEntityData.defineId(BasaltSpikeEntity.class, EntityDataSerializers.BOOLEAN);

    private LivingEntity caster;
    private int timer;

    public BasaltSpikeEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public BasaltSpikeEntity(EntityType<? extends BasaltSpikeEntity> type, Level world, LivingEntity caster, double x, double y, double z) {
        this(type, world);
        this.caster = caster;
        this.setPos(x, y, z);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
       // if(this.entityData.get(SUMMONED)) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.basalt_spike.summon", false));
            return PlayState.CONTINUE;
       // }
       // else {
         //   event.getController().setAnimation(new AnimationBuilder()
         //           .addAnimation("animation.basalt_spike.idle", false));
          //  return PlayState.CONTINUE;
       // }
    }


    @Override
    protected void defineSynchedData() {
        this.entityData.define(SUMMONED, false);
    }

    @Override
    public void tick() {
        super.tick();
        this.timer++;
        if(this.timer > 0 && this.timer < 50){
            this.entityData.set(SUMMONED, true);
        }
        if(this.timer > 15 && this.timer < 30) {
            for (LivingEntity livingentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox())) {
                this.dealDamageTo(livingentity);
            }
        }
        if(this.timer == 40){
            this.entityData.set(SUMMONED, false);
        }
        if(this.timer == 50){
            this.kill();
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
    }

    private void dealDamageTo(LivingEntity p_190551_1_) {
        if(this.caster != p_190551_1_) {
            p_190551_1_.hurt(DamageSource.mobAttack(this.caster), 6.0F);
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BasaltSpikeEntity>(this, "controller", 7, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
