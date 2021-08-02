package com.Wadoo.hyperion.Server.Entity;

import com.Wadoo.hyperion.Server.Entity.AI.BasaltOpenGoal;
import com.Wadoo.hyperion.Server.Entity.AI.MoveToLavaGoal;
import com.Wadoo.hyperion.Server.Entity.AI.PureBasaltGoal;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasaltCapslingEntity extends CreatureEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Boolean> OPEN = EntityDataManager.defineId(BasaltCapslingEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> BASALT = EntityDataManager.defineId(BasaltCapslingEntity.class, DataSerializers.BOOLEAN);

    public BasaltCapslingEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);

        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
        this.setPathfindingMalus(PathNodeType.LAVA, 0.0F);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving() || this.isInLava()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.basalt_capsling.walk", true));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.basalt_capsling.idle", true));
            return PlayState.CONTINUE;
        }
    }

    private <E extends IAnimatable> PlayState predicateOpen(AnimationEvent<E> event) {
        if(getOpen()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.basalt_capsling.open", true));
            return PlayState.CONTINUE;
        }
        else{
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.basalt_capsling.close", false));
            return PlayState.CONTINUE;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OPEN, false);
        this.entityData.define(BASALT, false);

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


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BasaltCapslingEntity>(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController<BasaltCapslingEntity>(this, "controllerOpen", 7, this::predicateOpen));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, BasaltCapslingEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new MoveToLavaGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new BasaltOpenGoal(this));
        this.goalSelector.addGoal(1, new PureBasaltGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.BASALT), false));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private void floatCapsling() {
        if (this.isInLava()) {
            ISelectionContext iselectioncontext = ISelectionContext.of(this);
            if (iselectioncontext.isAbove(FlowingFluidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA)) {
                this.onGround = true;
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, 0.05D, 0.0D));
                this.setDeltaMovement(this.getViewVector(2.0F));
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.floatCapsling();
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(Hand.MAIN_HAND);
        if(!getBasalt()) {
            if (itemStack.getItem() == Items.BASALT) {
                this.setItemSlot(EquipmentSlotType.MAINHAND, itemStack);
                itemStack.shrink(1);
                setBasalt(true);
                if (this.level.isClientSide) {
                    for (int i = 0; i < 40; ++i) {
                        if (i % 10 == 0) {
                            this.level.addParticle(ParticleTypes.HEART, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
                        }
                    }
                }
                return ActionResultType.SUCCESS;
            } else {
                return ActionResultType.FAIL;
            }
        }
        else{
            return ActionResultType.FAIL;
        }
    }
}
