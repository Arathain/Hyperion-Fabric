package com.Wadoo.hyperion.server.entity.ai;

import com.Wadoo.hyperion.server.entity.CapslingEntity;
import com.Wadoo.hyperion.server.registry.ItemRegister;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class PureBasaltGoal extends Goal {
    private final CapslingEntity entity;
    private int basaltTimer = 0;
    public PureBasaltGoal(CapslingEntity entity){
        this.entity = entity;
    }
    @Override
    public boolean canUse() {
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
            this.entity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            this.entity.setBasalt(false);
            ItemEntity entity = new ItemEntity(this.entity.level, this.entity.getX(), this.entity.getY(), this.entity.getZ(), new ItemStack(ItemRegister.PURE_BASALT.get(), 1));
            this.entity.level.addFreshEntity(entity);
        }
    }
}
