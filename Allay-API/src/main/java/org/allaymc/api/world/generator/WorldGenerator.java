package org.allaymc.api.world.generator;

import lombok.Getter;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Getter
public abstract class WorldGenerator {

    protected String preset;

    public WorldGenerator(String preset) {
        this.preset = preset;
    }

    //empty chunk -> 各种 noise-> biome -> calc height map -> generate -> carvers
    //feature -> structure place-> calc light -> spawn entity -> full
    public abstract void generate(ChunkGenerateContext context);

    public abstract String getGeneratorName();

    public abstract WorldGeneratorType getType();
}
