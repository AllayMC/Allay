package org.allaymc.exampleplugin;

import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.generator.ChunkGenerateContext;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.WorldGeneratorType;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 2024/5/18
 *
 * @author daoge_cmd
 */
public class RandomBlockGenerator extends WorldGenerator {

    protected static final BlockState[] BLOCK_STATE_TABLE = BlockStateHashPalette.getRegistry().getContent().values().toArray(BlockState[]::new);
    protected static final int BLOCK_STATE_TABLE_SIZE = BLOCK_STATE_TABLE.length;

    public RandomBlockGenerator(String preset) {
        super(preset);
    }

    @Override
    public void generate(ChunkGenerateContext context) {
        var chunk = context.chunk();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setHeight(x, z, 2);
                chunk.setBlockState(x, 1, z, getRandomBlockState());
            }
        }
    }

    protected BlockState getRandomBlockState() {
        return BLOCK_STATE_TABLE[ThreadLocalRandom.current().nextInt(BLOCK_STATE_TABLE_SIZE)];
    }

    @Override
    public String getGeneratorName() {
        return "RANDOM_BLOCK";
    }

    @Override
    public WorldGeneratorType getType() {
        return WorldGeneratorType.INFINITE;
    }
}
