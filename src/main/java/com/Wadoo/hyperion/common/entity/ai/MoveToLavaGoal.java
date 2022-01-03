package com.Wadoo.hyperion.common.entity.ai;

import com.Wadoo.hyperion.common.entity.CapslingEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class MoveToLavaGoal extends MoveToTargetPosGoal {
    private final CapslingEntity entity;

    public MoveToLavaGoal(CapslingEntity entity, double speedModifier) {
        super(entity, speedModifier, 8, 2);
        this.entity = entity;
    }

    @Override
    protected BlockPos getTargetPos() {
        return this.targetPos;
    }
    public boolean shouldContinue() {
        return !this.entity.isInLava() && this.isTargetPos(this.entity.world, this.targetPos);
    }

    public boolean canStart() {
        return !this.entity.isInLava() && super.canStart();
    }

    @Override
    public boolean shouldResetPath() {
        return this.tryingTime % 20 == 0;
    }


    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos).isOf(Blocks.LAVA) && world.getBlockState(pos.up()).canPathfindThrough(world, pos, NavigationType.LAND);
    }
}