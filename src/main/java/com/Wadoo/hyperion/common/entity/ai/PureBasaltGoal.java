package com.Wadoo.hyperion.common.entity.ai;

import com.Wadoo.hyperion.common.entity.CapslingEntity;
import com.Wadoo.hyperion.common.registry.HyperionItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class PureBasaltGoal extends Goal {
    private final CapslingEntity entity;
    private int basaltTimer = 0;
    public PureBasaltGoal(CapslingEntity entity){
        this.entity = entity;
    }
    @Override
    public boolean canStart() {
        if(this.entity.getBasalt()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void start() {
        super.start();
        basaltTimer = 700;
    }

    @Override
    public void tick() {
        super.tick();
        if(basaltTimer >= 0){
            basaltTimer--;

        }
        else{
            this.entity.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
            this.entity.setBasalt(false);
            ItemEntity entity = new ItemEntity(this.entity.world, this.entity.getX(), this.entity.getY(), this.entity.getZ(), new ItemStack(HyperionItems.PURE_BASALT, 1));
            this.entity.world.spawnEntity(entity);
        }
    }
}
