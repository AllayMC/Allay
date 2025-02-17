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
    final Chunk chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionInfo.OVERWORLD).toSafeChunk();

    @Test
    void testInvalidGetXXXMethodCall() {
        assertEquals(AIR.getDefaultState(), chunk.getBlockState(0, -10000, 0));
        assertThrows(IllegalArgumentException.class, () -> chunk.getBlockState(-1, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> chunk.getBlockState(16, 0, 16));

        assertEquals(BiomeId.PLAINS, chunk.getBiome(0, -10000, 0));
        assertThrows(IllegalArgumentException.class, () -> chunk.getBiome(-1, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> chunk.getBiome(16, 0, 16));

        assertThrows(IllegalArgumentException.class, () -> chunk.getBlockEntity(0, -10000, 0));
        assertThrows(IllegalArgumentException.class, () -> chunk.getBlockEntity(-1, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> chunk.getBlockEntity(16, 0, 16));
    }

    @Test
    void testInvalidSetXXXMethodCall() {
        assertThrows(IllegalArgumentException.class, () -> chunk.removeBlockEntity(0, -10000, 0));
        assertThrows(IllegalArgumentException.class, () -> chunk.removeBlockEntity(-1, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> chunk.removeBlockEntity(16, 0, 16));

        assertThrows(IllegalArgumentException.class, () -> chunk.addScheduledUpdate(0, -10000, 0, 5));
        assertThrows(IllegalArgumentException.class, () -> chunk.addScheduledUpdate(-1, 0, -1, 5));
        assertThrows(IllegalArgumentException.class, () -> chunk.addScheduledUpdate(16, 0, 16, 5));

        assertThrows(IllegalArgumentException.class, () -> chunk.hasScheduledUpdate(0, -10000, 0));
        assertThrows(IllegalArgumentException.class, () -> chunk.hasScheduledUpdate(-1, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> chunk.hasScheduledUpdate(16, 0, 16));

        assertThrows(IllegalArgumentException.class, () -> chunk.setBlockState(0, -10000, 0, AIR.getDefaultState()));
        assertThrows(IllegalArgumentException.class, () -> chunk.setBlockState(-1, 0, -1, AIR.getDefaultState()));
        assertThrows(IllegalArgumentException.class, () -> chunk.setBlockState(16, 0, 16, AIR.getDefaultState()));

        assertThrows(IllegalArgumentException.class, () -> chunk.getHeight(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> chunk.getHeight(16, 16));

        assertThrows(IllegalArgumentException.class, () -> chunk.setBiome(0, -10000, 0, BiomeId.PLAINS));
        assertThrows(IllegalArgumentException.class, () -> chunk.setBiome(-1, 0, -1, BiomeId.PLAINS));
        assertThrows(IllegalArgumentException.class, () -> chunk.setBiome(16, 0, 16, BiomeId.PLAINS));

        assertThrows(IllegalArgumentException.class, () -> chunk.getSection(114514));
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
        assertEquals(-64, chunk.getHeight(1, 1));
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
