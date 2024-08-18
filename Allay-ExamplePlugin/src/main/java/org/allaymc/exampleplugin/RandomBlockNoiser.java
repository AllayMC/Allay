package org.allaymc.exampleplugin;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.function.Noiser;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 2024/5/18
 *
 * @author daoge_cmd
 */
public class RandomBlockNoiser implements Noiser {

    protected static final BlockState[] BLOCK_STATE_TABLE = Registries.BLOCK_STATE_PALETTE.getContent().values().toArray(BlockState[]::new);
    protected static final int BLOCK_STATE_TABLE_SIZE = BLOCK_STATE_TABLE.length;

    protected BlockState getRandomBlockState() {
        return BLOCK_STATE_TABLE[ThreadLocalRandom.current().nextInt(BLOCK_STATE_TABLE_SIZE)];
    }

    @Override
    public boolean apply(NoiseContext context) {
        var chunk = context.getCurrentChunk();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setHeight(x, z, 2);
                chunk.setBlockState(x, 1, z, getRandomBlockState());
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "RANDOM_BLOCK_NOISER";
    }
}
