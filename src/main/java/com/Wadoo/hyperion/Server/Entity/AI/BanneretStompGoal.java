package com.Wadoo.hyperion.Server.Entity.AI;

import com.Wadoo.hyperion.Server.Entity.BasaltBanneretEntity;
import com.Wadoo.hyperion.Server.Entity.BasaltSpikeEntity;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraft.world.entity.ai.goal.Goal;

public class BanneretStompGoal extends Goal {
    private final BasaltBanneretEntity entity;
    private final int STOMP_TIMER = 47;
    private int cooldown = 400;
    private int timer;
    private double x;
    private double y;
    private double z;

    public BanneretStompGoal(BasaltBanneretEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        if(this.entity.getTarget() != null && this.entity.isAggressive()){
            return true;
        }
        else{
            this.entity.setAttackState(0);
            return false;
        }
    }

    @Override
    public boolean isInterruptable() {
        return true;
    }

    @Override
    public void start() {
        super.start();
        this.timer = 0;
    }

    @Override
    public void tick() {
        super.tick();
            if (this.timer <= 80) {
                this.entity.getNavigation().stop();
                this.entity.getLookControl().setLookAt(this.entity.getTarget(), 30, 30);
                this.timer++;
                this.entity.setAttackState(2);
                if(this.timer == 40){
                    x = this.entity.getTarget().getX();
                    y = this.entity.getTarget().getBlockY();
                    z = this.entity.getTarget().getZ();
                }
                if (this.timer == 55) {
                    BasaltSpikeEntity spikeEntity = new BasaltSpikeEntity(EntityRegister.BASALT_SPIKE.get(), this.entity.level, entity, x, y, z);
                    this.entity.level.addFreshEntity(spikeEntity);
                }
                if(this.timer > 70){
                    this.entity.setAttackState(0);
                }
            } else {
                this.entity.setAttackState(0);
                this.stop();
            }
    }

    @Override
    public void stop() {
        super.stop();
        this.timer = 0;

    }
}
