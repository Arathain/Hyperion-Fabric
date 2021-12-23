package com.Wadoo.hyperion.server.entity.ai;

import com.Wadoo.hyperion.server.entity.BasaltBanneretEntity;
import com.Wadoo.hyperion.server.entity.BasaltSpikeEntity;
import com.Wadoo.hyperion.server.registry.EntityRegister;
import net.minecraft.world.entity.ai.goal.Goal;

public class BanneretStompGoal extends Goal {
    private final BasaltBanneretEntity entity;
    private final int STOMP_TIMER = 47;
    private final int COOLDOWN = 20;
    private int timer;
    private int cooldownTimer;
    private double x;
    private double y;
    private double z;

    public BanneretStompGoal(BasaltBanneretEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        if(this.entity.getTarget() != null){
            System.out.println("not null: " + this.entity.getAttackState());
            return true;
        }
        else{
            System.out.println("null");
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
        this.cooldownTimer = 0;
    }

    @Override
    public void tick() {
        super.tick();
           if(this.cooldownTimer <= COOLDOWN){
               this.cooldownTimer++;
               this.entity.setAttackState(0);
               System.out.println("hi: " + this.cooldownTimer);
           }
           else{
               if(this.timer < STOMP_TIMER){
                   if (this.entity.getTarget() != null && this.timer <= 80) {
                       this.timer++;
                       this.entity.setAggressive(true);
                       this.entity.setAttackState(2);
                       this.entity.getNavigation().stop();
                       this.entity.setDeltaMovement(0, 0,0);
                       this.entity.getLookControl().setLookAt(this.entity.getTarget(), 30.0f, 30.0f);
                       x = this.entity.getTarget().getX();
                       y = this.entity.getTarget().getBlockY();
                       z = this.entity.getTarget().getZ();
                       if(this.timer == STOMP_TIMER){
                           BasaltSpikeEntity spikeEntity = new BasaltSpikeEntity(EntityRegister.BASALT_SPIKE.get(), this.entity.level, entity, x, y, z);
                           this.entity.level.addFreshEntity(spikeEntity);
                       }
                       if(this.timer > 78){
                           this.entity.setAttackState(0);
                           System.out.println("bye: " + this.timer);
                       }
                   }
                   else{
                       this.timer = 0;
                       this.cooldownTimer = 0;
                       this.entity.setAggressive(false);
                       this.entity.setAttackState(0);
                   }
               }
           }
    }

    @Override
    public void stop() {
        super.stop();
        this.timer = 0;
        this.entity.setAttackState(0);

    }
}
