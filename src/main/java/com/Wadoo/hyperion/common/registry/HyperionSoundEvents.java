package com.Wadoo.hyperion.common.registry;

import com.Wadoo.hyperion.Hyperion;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;


public class HyperionSoundEvents {
    private static final Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();

    public static final SoundEvent GRUSK_DEATH = create("grusk.death");
    public static final SoundEvent GRUSK_AMBIENT = create("grusk.ambient");
    public static final SoundEvent GRUSK_HURT = create("grusk.hurt");

    private static SoundEvent create(String name) {
        Identifier id = new Identifier(Hyperion.MOD_ID, name);
        SoundEvent soundEvent = new SoundEvent(id);
        SOUND_EVENTS.put(soundEvent, id);
        return soundEvent;
    }

    public static void init() {
        SOUND_EVENTS.keySet().forEach(effect -> Registry.register(Registry.SOUND_EVENT, SOUND_EVENTS.get(effect), effect));
    }

}
