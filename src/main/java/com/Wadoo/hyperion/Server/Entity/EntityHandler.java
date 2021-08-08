package com.Wadoo.hyperion.Server.Entity;
import com.Wadoo.hyperion.Hyperion;
import com.Wadoo.hyperion.Server.Register.EntityRegister;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hyperion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EntityHandler {
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(EntityRegister.BASALT_CAPSLING.get(), MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH,5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.278D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.3D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .build());

        event.put(EntityRegister.WARPED_FUNGLING.get(), MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH,25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.12D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .build());
    }

}