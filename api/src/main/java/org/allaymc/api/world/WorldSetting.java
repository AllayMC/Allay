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

/**
 * @author daoge_cmd
 */
@Builder
@Getter
@Accessors(fluent = true)
public class WorldSetting extends OkaeriConfig {
    /**
     * A field indicates whether the world is runtime only, which means that the world will not be saved
     * to the configuration file when the server shutdown. Therefore, it won't be loaded after the server
     * restarted because the setting for this world does not exist in the configuration file
     * <p>
     * This is useful for the world created for game room which will be deleted when shutdown.
     */
    @Setter
    @Exclude
    private boolean runtimeOnly;

    @Setter
    @Comment("If set to false, the world will not be loaded")
    @CustomKey("enable")
    @Builder.Default
    private boolean enable = true;

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
    @Builder
    public static class DimensionSettings extends OkaeriConfig {
        @CustomKey("generator-type")
        @Builder.Default
        private String generatorType = "VOID";

        @Comment("A preset string that will be passed to the generator instance. The format of the preset string depends")
        @Comment("on the generator implementation, for example the generator implementation can require text preset to a")
        @Comment("JSON format")
        @CustomKey("generator-preset")
        @Builder.Default
        private String generatorPreset = "";
    }
}
