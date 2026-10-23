package org.allaymc.server.world.chunk;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.poi.PoiTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.block.type.BlockTypes.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code AllayChunkTest} checks
 * safe chunk coordinate handling, independent data layers, height
 * caching, and point-of-interest updates.
 *
 * @author Cool_Loong
 */
@Slf4j
@ExtendWith(AllayTestExtension.class)
class AllayChunkTest {
    /**
     * {@code chunk} holds an independent empty Overworld chunk per test
     * instance. Each test mutates this chunk without sharing it across methods.
     */
    final Chunk chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD).toSafeChunk();

    /**
     * Checks out-of-height block and biome defaults alongside rejected local
     * horizontal coordinates. Block-entity reads instead reject invalid height,
     * preserving the different lookup contracts.
     */
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

    /**
     * Checks invalid coordinates across mutation, scheduling, height, and
     * section access. Every exercised operation must reject its invalid local
     * position or section index with an argument error.
     */
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

    /**
     * Writes wood and water into separate layers at one position. Reading each
     * layer must return its own state without replacing the other layer.
     */
    @Test
    void testUpdateBlockState() {
        chunk.setBlockState(0, 0, 0, OAK_WOOD.getDefaultState(), 0);
        chunk.setBlockState(0, 0, 0, WATER.getDefaultState(), 1);
        assertEquals(OAK_WOOD.getDefaultState(), chunk.getBlockState(0, 0, 0, 0));
        assertEquals(WATER.getDefaultState(), chunk.getBlockState(0, 0, 0, 1));
    }

    /**
     * Replaces the default biome at one valid block position with cherry grove.
     * Reading that position must return the newly assigned biome type.
     */
    @Test
    void testUpdateBiome() {
        chunk.setBiome(0, 10, 0, BiomeTypes.CHERRY_GROVE);
        assertEquals(BiomeTypes.CHERRY_GROVE, chunk.getBiome(0, 10, 0));
    }

    /**
     * Adds blocks at progressively different heights in one column. The cached
     * height must follow the highest non-air block and remain unchanged after a
     * lower insertion.
     */
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

    /**
     * Adds a barrel block entity at a valid local position and then removes it.
     * Lookup must first find the entity and subsequently return {@code null}.
     */
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

    /**
     * Registers a portal point of interest at one local position. Reading the
     * same position must return the registered type from the chunk mapping.
     */
    @Test
    void testAddAndGetPoi() {
        var type = PoiTypes.NETHER_PORTAL;
        chunk.addPoi(3, 64, 7, type);
        assertEquals(type, chunk.getPoi(3, 64, 7));
    }

    /**
     * Adds and removes a portal point of interest at one position. The entry
     * must be visible before removal and absent in the subsequent lookup.
     */
    @Test
    void testRemovePoi() {
        var type = PoiTypes.NETHER_PORTAL;
        chunk.addPoi(5, 100, 10, type);
        assertNotNull(chunk.getPoi(5, 100, 10));
        chunk.removePoi(5, 100, 10);
        assertNull(chunk.getPoi(5, 100, 10));
    }

    /**
     * Starts with an empty point-of-interest mapping and inserts two distinct
     * positions. The exposed mapping must reflect both entries in its reported
     * size.
     */
    @Test
    void testGetPoiEntries() {
        assertTrue(chunk.getPoiEntries().isEmpty());
        chunk.addPoi(0, 0, 0, PoiTypes.NETHER_PORTAL);
        chunk.addPoi(15, 64, 15, PoiTypes.NETHER_PORTAL);
        assertEquals(2, chunk.getPoiEntries().size());
    }

    /**
     * Queries a valid position in a chunk with no point-of-interest entries.
     * The absent entry must be represented by {@code null} rather than a default type.
     */
    @Test
    void testPoiNullWhenNotSet() {
        assertNull(chunk.getPoi(8, 32, 8));
    }

    /**
     * Exercises add, remove, and lookup with invalid point-of-interest
     * coordinates. All three operations must reject out-of-height and
     * out-of-range horizontal positions with argument errors.
     */
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
