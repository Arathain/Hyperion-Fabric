package com.Wadoo.hyperion.common.entity.ai;

import com.Wadoo.hyperion.common.entity.CapslingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

public class BasaltOpenGoal extends Goal {
    protected final CapslingEntity entity;
    private static final TargetPredicate TEMP_TARGETING = TargetPredicate.createNonAttackable().setBaseMaxDistance(15.0D).ignoreVisibility();
    private PlayerEntity player;


    public BasaltOpenGoal(CapslingEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canStart() {
        this.player = this.entity.world.getClosestPlayer(TEMP_TARGETING, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
        if(this.player != null){
            return true;
        }
        else{
            return false;

        }
    }

    public boolean shouldContinue() {
        if (!this.player.isAlive()) {
            return false;
        } else if (this.entity.squaredDistanceTo(this.player) > (double)(15.0D * 15.0D)) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void tick() {
        super.tick();
        if (this.player.squaredDistanceTo(this.entity) < 20.0D && this.entity.getCapslingAcceptedItems().test(this.player.getStackInHand(player.getActiveHand()))){
            if(!entity.getBasalt()) {
                this.entity.setOpen(true);
            }
            else{
                this.entity.setOpen(false);
            }
        }
        else {
            this.entity.setOpen(false);
        }
    }
}