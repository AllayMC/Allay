package org.allaymc.api.world;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Exclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * Allay Project 2024/2/3
 *
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
public class WorldConfig extends OkaeriConfig {

    @Exclude
    public static final String DEFAULT_WORLD_NAME = "Bedrock Level";
    @Exclude
    public static final WorldSettings DEFAULT = WorldSettings.builder()
            .storageType("LEVELDB")
            .overworld(new WorldSettings.DimensionSettings("FLAT", ""))
            .build();

    private Map<String, WorldSettings> worlds = Map.of(DEFAULT_WORLD_NAME, DEFAULT);

    @CustomKey("default-world")
    @Setter
    private String defaultWorld = DEFAULT_WORLD_NAME;

    @Builder
    @Getter
    @Accessors(fluent = true)
    public static class WorldSettings extends OkaeriConfig {
        @CustomKey("storage-type")
        private String storageType;
        private DimensionSettings overworld;
        @Comment("If you don't want to have nether / the end in this world, left it null")
        private DimensionSettings nether;
        @CustomKey("the-end")
        private DimensionSettings theEnd;

        @AllArgsConstructor
        @Getter
        @Accessors(fluent = true)
        public static class DimensionSettings extends OkaeriConfig {
            @CustomKey("generator-type")
            private String generatorType;
            @CustomKey("generator-preset")
            private String generatorPreset;
        }
    }
}
