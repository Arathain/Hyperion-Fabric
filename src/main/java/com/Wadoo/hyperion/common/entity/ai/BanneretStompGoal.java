package com.Wadoo.hyperion.common.entity.ai;

import com.Wadoo.hyperion.common.entity.BasaltBanneretEntity;
import com.Wadoo.hyperion.common.entity.BasaltSpikeEntity;
import com.Wadoo.hyperion.common.registry.HyperionEntities;
import net.minecraft.entity.ai.goal.Goal;

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
    public boolean canStart() {
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
    public boolean canStop() {
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
                       this.entity.setAttacking(true);
                       this.entity.setAttackState(2);
                       this.entity.getNavigation().stop();
                       this.entity.setVelocity(0, 0,0);
                       this.entity.getLookControl().lookAt(this.entity.getTarget(), 30.0f, 30.0f);
                       x = this.entity.getTarget().getX();
                       y = this.entity.getTarget().getBlockY();
                       z = this.entity.getTarget().getZ();
                       if(this.timer == STOMP_TIMER){
                           BasaltSpikeEntity spikeEntity = new BasaltSpikeEntity(HyperionEntities.BASALT_SPIKE, this.entity.world, entity, x, y, z);
                           this.entity.world.spawnEntity(spikeEntity);
                       }
                       if(this.timer > 78){
                           this.entity.setAttackState(0);
                           System.out.println("bye: " + this.timer);
                       }
                   }
                   else{
                       this.timer = 0;
                       this.cooldownTimer = 0;
                       this.entity.setAttacking(false);
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
