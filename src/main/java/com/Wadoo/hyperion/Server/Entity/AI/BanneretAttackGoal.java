package com.Wadoo.hyperion.Server.Entity.AI;

import com.Wadoo.hyperion.Server.Entity.BasaltBanneretEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.Hand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class BanneretAttackGoal extends Goal {
    protected BasaltBanneretEntity entity;
    protected double speedModifier;
    protected boolean followInNotSeen;
    protected int attackFrame;
    protected int attackTimer;
    protected int endFrame;

    public BanneretAttackGoal(BasaltBanneretEntity entity, double speedModifier, boolean followIfNotSeen, int attackFrame, int endFrame) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.followInNotSeen = followIfNotSeen;
        this.attackFrame = attackFrame;
        this.endFrame = endFrame;
    }

    @Override
    public boolean canUse() {
        if(this.entity.getTarget() != null){
            if(this.entity.distanceToSqr(this.entity.getTarget()) < 15.0D){
                this.entity.setAggressive(true);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void tick() {
        super.tick();
        if(this.attackTimer < this.endFrame){
            this.attackTimer++;
            this.entity.setAttackState(1);
        }
        else{
            this.entity.setAttackState(0);

            this.attackTimer = 0;
            this.entity.setAttackState(0);
        }

        if(this.attackTimer == this.attackFrame){
            this.checkAndAttack(this.entity, this.entity.getTarget());
        }
    }

    public void checkAndAttack(BasaltBanneretEntity entity, LivingEntity target){
        if(this.entity.distanceToSqr(target) < this.getAttackReachSqr(this.entity)){
            this.entity.doHurtTarget(target);
        }

    }

    @Override
    public void stop() {
        super.stop();
    }

    protected double getAttackReachSqr(LivingEntity p_179512_1_) {
        return (double)(this.entity.getBbWidth() * 2.0F * this.entity.getBbWidth() * 2.0F + p_179512_1_.getBbWidth());
    }
}
