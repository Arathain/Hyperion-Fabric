package com.Wadoo.hyperion.Server.Entity.AI;

import com.Wadoo.hyperion.Server.Entity.CapslingEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;

public class BasaltOpenGoal extends Goal {
    protected final EntityPredicate target;
    protected final CapslingEntity entity;
    private PlayerEntity player;


    public BasaltOpenGoal(CapslingEntity entity){
        this.entity = entity;
        this.target = (new EntityPredicate()).range((double)15.0).allowSameTeam().allowInvulnerable().allowNonAttackable().selector((p_220715_1_) -> EntityPredicates.notRiding(entity).test(p_220715_1_));
    }

    @Override
    public boolean canUse() {
        this.player = this.entity.level.getNearestPlayer(this.target, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
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
        if (this.player.distanceToSqr(this.entity) < 20.0D && this.player.getItemInHand(Hand.MAIN_HAND).getItem() == Items.BASALT) {
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