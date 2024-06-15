package org.allaymc.api.world.generator;

import lombok.Getter;
import org.allaymc.api.world.Dimension;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Getter
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
}
