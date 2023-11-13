package org.allaymc.api.world.generator;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface Generator {
    //empty chunk -> 各种 noise-> biome -> calc height map -> generate -> carvers
    //feature -> structure place-> calc light -> spawn entity -> full
    void generate(ChunkGenerateContext context);

    String getGeneratorName();

    GeneratorType getType();
}
