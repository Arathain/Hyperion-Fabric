package com.Wadoo.hyperion.common.util.config;

import com.Wadoo.hyperion.Hyperion;
import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import draylar.omegaconfig.api.Syncing;

public class HyperionConfig implements Config {
    @Syncing
    @Comment("""
            Basalt Capsling Spawn Weight. The Default Value is 20
            """)
    public int basaltCapslingWeight = 20;
    @Syncing
    @Comment("""
            The smallest group size Basalt Capslings can spawn in. The Default Value is 2
            """)
    public int basaltCapslingMin = 2;
    @Syncing
    @Comment("""
            The largest group size Basalt Capslings can spawn in. The Default Value is 5
            """)
    public int basaltCapslingMax = 5;



    @Override
    public String getName() {
        return "hyperion";
    }
    @Override
    public String getModid() {
        return Hyperion.MOD_ID;
    }
    @Override
    public String getExtension() {
        return "toml";
    }
}
