package com.Wadoo.hyperion.common.entity;

import com.Wadoo.hyperion.common.entity.ai.BasaltOpenGoal;
import com.Wadoo.hyperion.common.entity.ai.MoveToLavaGoal;
import com.Wadoo.hyperion.common.entity.ai.PureBasaltGoal;
import com.Wadoo.hyperion.common.registry.HyperionItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Optional;
import java.util.Random;

public class CapslingEntity extends PathAwareEntity implements IAnimatable, IAnimationTickable, Bucketable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Boolean> OPEN = DataTracker.registerData(CapslingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> BASALT = DataTracker.registerData(CapslingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> ANIM_STATE = DataTracker.registerData(CapslingEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(CapslingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private final Ingredient CapslingAcceptedItems = Ingredient.ofItems(Items.BASALT, Items.POLISHED_BASALT, Items.SMOOTH_BASALT);

    public CapslingEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.LAVA, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
    }

    public static DefaultAttributeContainer.Builder createCapslingAttributes() {
        return MobEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 28.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.278D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.3D)
                .add(EntityAttributes.GENERIC_ARMOR, 4D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.getOpen()) {
            if (event.isMoving() || this.isInLava() && this.getAttacker() == null) {
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
            if (event.isMoving() || this.isInLava() && this.getAttacker() == null) {
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
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(OPEN, false);
        this.dataTracker.startTracking(BASALT, false);
        this.dataTracker.startTracking(ANIM_STATE, 0);
        this.dataTracker.startTracking(FROM_BUCKET, false);
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        return true;
    }

    public boolean getOpen() {
        return this.dataTracker.get(OPEN);
    }

    public void setOpen(boolean open){
        this.dataTracker.set(OPEN, open);
    }

    public boolean getBasalt(){
        return this.dataTracker.get(BASALT);
    }

    public void setBasalt(boolean basalt){
        this.dataTracker.set(BASALT, basalt);
    }

    public Ingredient getCapslingAcceptedItems() {
        return CapslingAcceptedItems;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound NBT) {
        super.writeCustomDataToNbt(NBT);
        NBT.putBoolean("Basalt", this.getBasalt());
        NBT.putBoolean("fromBucket", this.isFromBucket());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound NBT) {
        super.readCustomDataFromNbt(NBT);
        setBasalt(NBT.getBoolean("Basalt"));
        setFromBucket(NBT.getBoolean("fromBucket"));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<CapslingEntity>(this, "controller", 8, this::predicate));
        data.addAnimationController(new AnimationController<CapslingEntity>(this, "controllerOpen", 8, this::predicateOpen));

    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, CapslingEntity.class, 8.0F));
        this.goalSelector.add(2, new MoveToLavaGoal(this, 1.0D));
        this.goalSelector.add(2, new TemptGoal(this, 1.0D, Ingredient.ofItems(Items.BASALT), false));
        this.goalSelector.add(4, new FleeEntityGoal<>(this, GruskEntity.class, 8.0F, 1.6D, 1.4D, (p_28590_) -> {
            return !this.getBasalt();
        }));
        this.goalSelector.add(2, new FleeEntityGoal<BasaltBanneretEntity>(this, BasaltBanneretEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new BasaltOpenGoal(this));
        this.goalSelector.add(1, new PureBasaltGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private void floatCapsling() {
        if (this.isInLava()) {
            ShapeContext iselectioncontext = ShapeContext.of(this);
            if (iselectioncontext.isAbove(FluidBlock.COLLISION_SHAPE, this.getBlockPos(), true) && !this.world.getFluidState(this.getBlockPos().up()).isIn(FluidTags.LAVA)) {
                this.onGround = true;
            } else {
                this.setVelocity(this.getVelocity().multiply(0.5D).add(0.0D, 0.22D, 0.0D));
                this.setMovementSpeed(1.2f);
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
                    this.world.addParticle(ParticleTypes.FLAME, this.getX() + (this.getRandom().nextBoolean() ? this.getRandom().nextFloat(0.5f) : -this.getRandom().nextFloat(0.5f)), this.getX() - 0.25D, this.getZ() + (this.getRandom().nextBoolean() ? this.getRandom().nextFloat(0.5f) : -this.getRandom().nextFloat(0.5f)), 0, 0.08d, 0);
                }
            }
            if (random.nextFloat() < 0.05F) {
                for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                    this.world.addParticle(ParticleTypes.SMOKE, this.getX() + (this.getRandom().nextBoolean() ? this.getRandom().nextFloat(0.5f) : -this.getRandom().nextFloat(0.5f)), this.getX() - 0.25D, (this.getRandom().nextBoolean() ? this.getRandom().nextFloat(0.5f) : -this.getRandom().nextFloat(0.5f)), 0, 0.08d, 0);
                }
            }
        }
    }

    @Override
    public int tickTimer() {
        return age;
    }

    @Override
    protected void dropXp() {
        super.dropXp();
    }

    @Override
    protected int getXpToDrop(PlayerEntity p_21511_) {
        return super.getXpToDrop(p_21511_);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if(!getBasalt()) {
            if (this.CapslingAcceptedItems.test(itemStack)) {
                this.setStackInHand(Hand.MAIN_HAND, itemStack);
                itemStack.decrement(1);
                setBasalt(true);
                if (this.world.isClient) {
                    for (int i = 0; i < 40; ++i) {
                        if (i % 10 == 0) {
                            this.world.addParticle(ParticleTypes.HEART, this.getX() + (this.getRandom().nextBoolean() ? this.getRandom().nextFloat(0.5f) : -this.getRandom().nextFloat(0.5f)), this.getX() - 0.25D, this.getZ() + (this.getRandom().nextBoolean() ? this.getRandom().nextFloat(0.5f) : -this.getRandom().nextFloat(0.5f)), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
                        }
                    }
                }
                return ActionResult.SUCCESS;
            } else {
                return (ActionResult) tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
            }
        }
        else{
            return (ActionResult) tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
        }
    }

    public static boolean canSpawn(EntityType<CapslingEntity> capslingEntityEntityType, ServerWorldAccess serverWorldAccess, SpawnReason spawnReason, BlockPos blockPos, Random random) {
        return canMobSpawn(capslingEntityEntityType, serverWorldAccess, spawnReason, blockPos, random) || spawnReason == SpawnReason.SPAWNER;
    }

    @Override
    public boolean isFromBucket() {
        return dataTracker.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        dataTracker.set(FROM_BUCKET, fromBucket);
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("fromBucket", this.isFromBucket());
        nbtCompound.putBoolean("Basalt", this.getBasalt());

    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
        this.dataTracker.set(FROM_BUCKET, nbt.getBoolean("fromBucket"));
        this.dataTracker.set(BASALT, nbt.getBoolean("Basalt"));
    }

    @Override
    public ItemStack getBucketItem() {
        return HyperionItems.CAPSLING_BUCKET.getDefaultStack();
    }

    @Override
    public SoundEvent getBucketedSound() {
        return SoundEvents.ITEM_BUCKET_FILL_LAVA;
    }
    public static <T extends LivingEntity> Optional<ActionResult> tryBucket(PlayerEntity player, Hand hand, T entity) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.LAVA_BUCKET && entity.isAlive()) {
            entity.playSound(((Bucketable)((Object)entity)).getBucketedSound(), 1.0f, 1.0f);
            ItemStack itemStack2 = ((Bucketable)((Object)entity)).getBucketItem();
            ((Bucketable)((Object)entity)).copyDataToStack(itemStack2);
            ItemStack itemStack3 = ItemUsage.exchangeStack(itemStack, player, itemStack2, false);
            player.setStackInHand(hand, itemStack3);
            World world = entity.world;
            if (!world.isClient) {
                Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity)player, itemStack2);
            }
            entity.discard();
            return Optional.of(ActionResult.success(world.isClient));
        }
        return Optional.empty();
    }
}
