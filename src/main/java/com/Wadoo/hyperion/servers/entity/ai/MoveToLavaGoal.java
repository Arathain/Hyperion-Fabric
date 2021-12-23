package com.Wadoo.hyperion.servers.entity.ai;

import com.Wadoo.hyperion.servers.entity.CapslingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathComputationType;

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

    protected boolean isValidTarget(LevelReader worldReader, BlockPos blockPos) {
        return worldReader.getBlockState(blockPos).is(Blocks.LAVA) && worldReader.getBlockState(blockPos.above()).isPathfindable(worldReader, blockPos, PathComputationType.LAND);
    }
}