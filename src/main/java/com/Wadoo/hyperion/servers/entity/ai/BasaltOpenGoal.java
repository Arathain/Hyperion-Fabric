package com.Wadoo.hyperion.servers.entity.ai;

import com.Wadoo.hyperion.servers.entity.CapslingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

public class BasaltOpenGoal extends Goal {
    protected final CapslingEntity entity;
    private static final TargetingConditions TEMP_TARGETING = TargetingConditions.forNonCombat().range(15.0D).ignoreLineOfSight();
    private Player player;


    public BasaltOpenGoal(CapslingEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        this.player = this.entity.level.getNearestPlayer(this.TEMP_TARGETING, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
        if(this.player != null){
            return true;
        }
        else{
            return false;

        }
    }

    public boolean canContinueToUse() {
        if (!this.player.isAlive()) {
            return false;
        } else if (this.entity.distanceToSqr(this.player) > (double)(15.0D * 15.0D)) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void tick() {
        super.tick();
        if (this.player.distanceToSqr(this.entity) < 20.0D && this.entity.getCapslingAcceptedItems().test(this.player.getItemInHand(InteractionHand.MAIN_HAND))){
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