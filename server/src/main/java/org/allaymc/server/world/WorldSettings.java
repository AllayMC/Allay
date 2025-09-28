package org.allaymc.server.world;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Exclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.allaymc.api.world.WorldSetting;

import java.util.Map;

/**
 * WorldSettings contains the settings of all worlds in the server.
 *
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
public class WorldSettings extends OkaeriConfig {
    @Exclude
    public static final String DEFAULT_WORLD_NAME = "world";
    @Exclude
    public static final WorldSetting DEFAULT = WorldSetting.builder()
            .storageType("LEVELDB")
            .overworld(new WorldSetting.DimensionSettings("FLAT", ""))
            .build();

    @CustomKey("worlds")
    private Map<String, WorldSetting> worlds = Map.of(DEFAULT_WORLD_NAME, DEFAULT);

    @CustomKey("default-world")
    @Setter
    private String defaultWorld = DEFAULT_WORLD_NAME;
}
