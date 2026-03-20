package org.allaymc.server.world.chunk;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.poi.PoiTypes;
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

        assertEquals(BiomeTypes.PLAINS, chunk.getBiome(0, -10000, 0));
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

        assertThrows(IllegalArgumentException.class, () -> chunk.setBiome(0, -10000, 0, BiomeTypes.PLAINS));
        assertThrows(IllegalArgumentException.class, () -> chunk.setBiome(-1, 0, -1, BiomeTypes.PLAINS));
        assertThrows(IllegalArgumentException.class, () -> chunk.setBiome(16, 0, 16, BiomeTypes.PLAINS));

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
        chunk.setBiome(0, 10, 0, BiomeTypes.CHERRY_GROVE);
        assertEquals(BiomeTypes.CHERRY_GROVE, chunk.getBiome(0, 10, 0));
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

    @Test
    void testAddAndGetPoi() {
        var type = PoiTypes.NETHER_PORTAL;
        chunk.addPoi(3, 64, 7, type);
        assertEquals(type, chunk.getPoi(3, 64, 7));
    }

    @Test
    void testRemovePoi() {
        var type = PoiTypes.NETHER_PORTAL;
        chunk.addPoi(5, 100, 10, type);
        assertNotNull(chunk.getPoi(5, 100, 10));
        chunk.removePoi(5, 100, 10);
        assertNull(chunk.getPoi(5, 100, 10));
    }

    @Test
    void testGetPoiEntries() {
        assertTrue(chunk.getPoiEntries().isEmpty());
        chunk.addPoi(0, 0, 0, PoiTypes.NETHER_PORTAL);
        chunk.addPoi(15, 64, 15, PoiTypes.NETHER_PORTAL);
        assertEquals(2, chunk.getPoiEntries().size());
    }

    @Test
    void testPoiNullWhenNotSet() {
        assertNull(chunk.getPoi(8, 32, 8));
    }

    @Test
    void testInvalidPoiMethodCall() {
        var type = PoiTypes.NETHER_PORTAL;
        assertThrows(IllegalArgumentException.class, () -> chunk.addPoi(0, -10000, 0, type));
        assertThrows(IllegalArgumentException.class, () -> chunk.addPoi(-1, 0, -1, type));
        assertThrows(IllegalArgumentException.class, () -> chunk.addPoi(16, 0, 16, type));

        assertThrows(IllegalArgumentException.class, () -> chunk.removePoi(0, -10000, 0));
        assertThrows(IllegalArgumentException.class, () -> chunk.removePoi(-1, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> chunk.removePoi(16, 0, 16));

        assertThrows(IllegalArgumentException.class, () -> chunk.getPoi(0, -10000, 0));
        assertThrows(IllegalArgumentException.class, () -> chunk.getPoi(-1, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> chunk.getPoi(16, 0, 16));
    }
}
