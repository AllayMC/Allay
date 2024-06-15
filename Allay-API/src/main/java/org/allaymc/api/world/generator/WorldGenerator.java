package org.allaymc.api.world.generator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.world.Dimension;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public abstract class WorldGenerator {

    protected String preset;
    protected Dimension dimension;

    public WorldGenerator(String preset) {
        this.preset = preset;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    //empty chunk -> 各种 noise-> biome -> calc height map -> generate -> carvers
    //feature -> structure place-> calc light -> spawn entity -> full
    public abstract void generate(ChunkGenerateContext context);

    public abstract String getGeneratorName();

    public abstract WorldGeneratorType getType();

    public static Map<String, String> parseOptions(String preset) {
        var splits = AllayStringUtils.fastSplit(preset, ";");
        var options = new HashMap<String, String>();
        for(var split : splits) {
            if (!split.contains("=")) {
                log.warn("Invalid option: {}", split);
                continue;
            }
            var kv = AllayStringUtils.fastTwoPartSplit(split, "=", "");
            options.put(kv[0], kv[1]);
        }
        return options;
    }
}
