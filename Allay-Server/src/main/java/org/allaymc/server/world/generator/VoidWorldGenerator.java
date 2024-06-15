package org.allaymc.server.world.generator;

import org.allaymc.api.world.generator.ChunkGenerateContext;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.WorldGeneratorType;

import static org.allaymc.api.world.generator.WorldGeneratorType.INFINITE;

/**
 * Allay Project 2024/2/3
 *
 * @author daoge_cmd
 */
public class VoidWorldGenerator extends WorldGenerator {
    public VoidWorldGenerator(String preset) {
        super(preset);
    }

    @Override
    public void generate(ChunkGenerateContext context) {
        // Do nothing!
    }

    @Override
    public String getGeneratorName() {
        return "VOID";
    }

    @Override
    public WorldGeneratorType getType() {
        return INFINITE;
    }
}
