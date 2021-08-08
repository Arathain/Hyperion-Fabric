package com.Wadoo.hyperion.Server.Entity.AI;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class WarpedFunglingEntity extends MonsterEntity implements IAnimatable {

    public WarpedFunglingEntity(EntityType<? extends MonsterEntity> entity, World world) {
        super(entity, world);
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return null;
    }
}
