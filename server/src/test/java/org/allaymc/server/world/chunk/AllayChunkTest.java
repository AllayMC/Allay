package org.allaymc.server.world.chunk;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeId;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.block.type.BlockTypes.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cool_Loong
 */
@Slf4j
@ExtendWith(AllayTestExtension.class)
class AllayChunkTest {
    final Chunk chunk = new AllayChunk(AllayUnsafeChunk.builder().emptyChunk(0, 0, DimensionInfo.OVERWORLD));

    @Test
    void testInvalidGetBlockStateMethodCall() {
        assertEquals(AIR.getDefaultState(), chunk.getBlockState(0, -10000, 0));
    }

    @Test
    void testUpdateBlockState() {
        chunk.setBlockState(0, 0, 0, OAK_WOOD.getDefaultState(), 0);
        chunk.setBlockState(0, 0, 0, WATER.getDefaultState(), 1);
        assertEquals(OAK_WOOD.getDefaultState(), chunk.getBlockState(0, 0, 0, 0));
        assertEquals(WATER.getDefaultState(), chunk.getBlockState(0, 0, 0, 1));
    }

    @Test
    void testUpdateBiome() {
        chunk.setBiome(0, 10, 0, BiomeId.CHERRY_GROVE);
        assertEquals(BiomeId.CHERRY_GROVE, chunk.getBiome(0, 10, 0));
    }

    @Test
    void testUpdateHeight() {
        assertEquals(-65, chunk.getHeight(1, 1));
        chunk.setBlockState(1, 1, 1, OAK_WOOD.getDefaultState());
        assertEquals(1, chunk.getHeight(1, 1));
        chunk.setBlockState(1, 5, 1, OAK_WOOD.getDefaultState());
        assertEquals(5, chunk.getHeight(1, 1));
        chunk.setBlockState(1, 0, 1, OAK_WOOD.getDefaultState());
        assertEquals(5, chunk.getHeight(1, 1));
    }

    @Test
    void testUpdateBlockEntity() {
        var blockEntity = BlockEntityTypes.BARREL.createBlockEntity(
                BlockEntityInitInfo
                        .builder()
                        .pos(11, 45, 14)
                        .build()
        );
        chunk.addBlockEntity(blockEntity);
        assertNotNull(chunk.getBlockEntity(11, 45, 14));
        chunk.removeBlockEntity(11, 45, 14);
        assertNull(chunk.getBlockEntity(11, 45, 14));
    }
}
