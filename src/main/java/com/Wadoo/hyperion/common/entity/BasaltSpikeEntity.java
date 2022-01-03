package com.Wadoo.hyperion.common.entity;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasaltSpikeEntity extends Entity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Boolean> SUMMONED = DataTracker.registerData(BasaltSpikeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private LivingEntity caster;
    private int timer;

    public BasaltSpikeEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    public BasaltSpikeEntity(EntityType<? extends BasaltSpikeEntity> type, World world, LivingEntity caster, double x, double y, double z) {
        this(type, world);
        this.caster = caster;
        this.setPos(x, y, z);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
       // if(this.dataTracker.get(SUMMONED)) {
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
    protected void initDataTracker() {
        this.dataTracker.startTracking(SUMMONED, false);
    }

    @Override
    public void tick() {
        super.tick();
        this.timer++;
        if(this.timer > 0 && this.timer < 50){
            this.dataTracker.set(SUMMONED, true);
        }
        if(this.timer > 15 && this.timer < 30) {
            for (LivingEntity livingentity : this.world.getEntitiesByClass(LivingEntity.class, this.getBoundingBox(), EntityPredicates.VALID_LIVING_ENTITY)) {
                this.dealDamageTo(livingentity);
            }
        }
        if(this.timer == 40){
            this.dataTracker.set(SUMMONED, false);
        }
        if(this.timer == 50){
            this.kill();
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    private void dealDamageTo(LivingEntity p_190551_1_) {
        if(this.caster != p_190551_1_) {
            p_190551_1_.damage(DamageSource.mob(this.caster), 6.0F);
        }
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
