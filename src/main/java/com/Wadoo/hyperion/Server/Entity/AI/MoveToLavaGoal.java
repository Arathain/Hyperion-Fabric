package com.Wadoo.hyperion.Server.Entity.AI;

import com.Wadoo.hyperion.Server.Entity.CapslingEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class MoveToLavaGoal extends MoveToBlockGoal {
    private final CapslingEntity entity;

    public MoveToLavaGoal(CapslingEntity entity, double speedModifier) {
        super(entity, speedModifier, 8, 2);
        this.entity = entity;
    }

    public BlockPos getMoveToTarget() {
        return this.blockPos;
    }

    public boolean canContinueToUse() {
        return !this.entity.isInLava() && this.isValidTarget(this.entity.level, this.blockPos);
    }

    public boolean canUse() {
        return !this.entity.isInLava() && super.canUse();
    }

    public boolean shouldRecalculatePath() {
        return this.tryTicks % 20 == 0;
    }

    protected boolean isValidTarget(IWorldReader worldReader, BlockPos blockPos) {
        return worldReader.getBlockState(blockPos).is(Blocks.LAVA) && worldReader.getBlockState(blockPos.above()).isPathfindable(worldReader, blockPos, PathType.LAND);
    }
}