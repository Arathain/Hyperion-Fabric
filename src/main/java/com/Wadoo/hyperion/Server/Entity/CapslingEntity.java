package com.Wadoo.hyperion.server.entity;

import com.Wadoo.hyperion.server.entity.ai.BasaltOpenGoal;
import com.Wadoo.hyperion.server.entity.ai.MoveToLavaGoal;
import com.Wadoo.hyperion.server.entity.ai.PureBasaltGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.shapes.CollisionContext;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class CapslingEntity extends PathfinderMob implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Boolean> OPEN = SynchedEntityData.defineId(CapslingEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BASALT = SynchedEntityData.defineId(CapslingEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ANIM_STATE = SynchedEntityData.defineId(CapslingEntity.class, EntityDataSerializers.INT);

    private Ingredient CapslingAcceptedItems = Ingredient.of(Items.BASALT, Items.POLISHED_BASALT, Items.SMOOTH_BASALT);

    public CapslingEntity(EntityType<? extends PathfinderMob> type, Level world) {
        super(type, world);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.getOpen()) {
            if (event.isMoving() || this.isInLava() && this.getLastHurtByMob() == null) {
                event.getController().setAnimation(new AnimationBuilder()
                        .addAnimation("animation.capsling.walk", true));
                return PlayState.CONTINUE;
            }else {
                event.getController().setAnimation(new AnimationBuilder()
                        .addAnimation("animation.capsling.idle", true));
                return PlayState.CONTINUE;
            }
        }
        else{
            if (event.isMoving() || this.isInLava() && this.getLastHurtByMob() == null) {
                event.getController().setAnimation(new AnimationBuilder()
                        .addAnimation("animation.capsling.walk2", true));
                return PlayState.CONTINUE;
            } else {
                event.getController().setAnimation(new AnimationBuilder()
                        .addAnimation("animation.capsling.idle2", true));
                return PlayState.CONTINUE;
            }
        }
    }

    private <E extends IAnimatable> PlayState predicateOpen(AnimationEvent<E> event) {
        if(getOpen()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.capsling.open", true));
            return PlayState.CONTINUE;
        }
        else{
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.capsling.close", false));
            return PlayState.CONTINUE;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OPEN, false);
        this.entityData.define(BASALT, false);
        this.entityData.define(ANIM_STATE, 0);
    }

    public static boolean canSpawn(EntityType<CapslingEntity> type, ServerLevelAccessor world, MobSpawnType spawnReason, BlockPos pos, Random random) {
        return true;
    }

    public boolean getOpen() {
        return this.entityData.get(OPEN);
    }

    public void setOpen(boolean open){
        this.entityData.set(OPEN, open);
    }

    public boolean getBasalt(){
        return this.entityData.get(BASALT);
    }

    public void setBasalt(boolean basalt){
        this.entityData.set(BASALT, basalt);
    }

    public Ingredient getCapslingAcceptedItems() {
        return CapslingAcceptedItems;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag NBT) {
        super.addAdditionalSaveData(NBT);
        NBT.putBoolean("Basalt", this.getBasalt());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag NBT) {
        super.readAdditionalSaveData(NBT);
        setBasalt(NBT.getBoolean("Basalt"));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<CapslingEntity>(this, "controller", 8, this::predicate));
        data.addAnimationController(new AnimationController<CapslingEntity>(this, "controllerOpen", 8, this::predicateOpen));

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, CapslingEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new MoveToLavaGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, Ingredient.of(Items.BASALT), false));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, GruskEntity.class, 8.0F, 1.6D, 1.4D, (p_28590_) -> {
            return !this.getBasalt();
        }));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, BasaltBanneretEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new BasaltOpenGoal(this));
        this.goalSelector.addGoal(1, new PureBasaltGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private void floatCapsling() {
        if (this.isInLava()) {
            CollisionContext iselectioncontext = CollisionContext.of(this);
            if (iselectioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA)) {
                this.onGround = true;
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, 0.22D, 0.0D));
                this.setSpeed(1.2f);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.floatCapsling();
        if(this.getBasalt()) {
            if (random.nextFloat() < 0.11F) {
                for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                    this.level.addParticle(ParticleTypes.FLAME, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), 0, 0.08d, 0);
                }
            }
            if (random.nextFloat() < 0.05F) {
                for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                    this.level.addParticle(ParticleTypes.SMOKE, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), 0, 0.08d, 0);
                }
            }
        }
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    @Override
    protected int getExperienceReward(Player p_21511_) {
        return super.getExperienceReward(p_21511_);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        /*if (itemStack.getItem() == Items.BUCKET && this.isAlive()) {
            this.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            itemStack.shrink(1);
            ItemStack itemstack1 = new ItemStack(ItemRegister.CAPSLING_BUCKET.get());
            this.saveToBucketTag(itemstack1);
            if (!this.level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, itemstack1);
            }

            if (itemStack.isEmpty()) {
                player.setItemInHand(hand, itemstack1);
            } else if (!player.getInventory().add(itemstack1)) {
                player.drop(itemstack1, false);
            }

            this.remove(RemovalReason.DISCARDED);
            return InteractionResult.SUCCESS;
        }*/
        if(!getBasalt()) {
            if (this.CapslingAcceptedItems.test(itemStack)) {
                this.setItemSlot(EquipmentSlot.MAINHAND, itemStack);
                itemStack.shrink(1);
                setBasalt(true);
                if (this.level.isClientSide) {
                    for (int i = 0; i < 40; ++i) {
                        if (i % 10 == 0) {
                            this.level.addParticle(ParticleTypes.HEART, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
                        }
                    }
                }
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        }
        else{
            return InteractionResult.FAIL;
        }
    }
}
