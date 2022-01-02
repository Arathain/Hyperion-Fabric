package com.Wadoo.hyperion.server.registry;

import com.Wadoo.hyperion.Hyperion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegister {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Hyperion.MOD_ID);

    public static final RegistryObject<SoundEvent> GRUSK_DEATH = SOUNDS.register("grusk.death", () -> new SoundEvent(new ResourceLocation(Hyperion.MOD_ID, "grusk.death")));
    public static final RegistryObject<SoundEvent> GRUSK_AMBIENT = SOUNDS.register("grusk.ambient", () -> new SoundEvent(new ResourceLocation(Hyperion.MOD_ID, "grusk.ambient")));
    public static final RegistryObject<SoundEvent> GRUSK_HURT = SOUNDS.register("grusk.hurt", () -> new SoundEvent(new ResourceLocation(Hyperion.MOD_ID, "grusk.hurt")));

}
