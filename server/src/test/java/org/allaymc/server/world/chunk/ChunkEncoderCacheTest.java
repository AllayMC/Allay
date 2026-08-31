package org.allaymc.server.world.chunk;

import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;

import static org.allaymc.api.block.type.BlockTypes.AIR;
import static org.allaymc.api.block.type.BlockTypes.STONE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class ChunkEncoderCacheTest {

    @Test
    void testUnchangedSectionReturnsCachedBlob() {
        var chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD);
        byte[] first = ChunkEncoder.encodeSectionBlob(chunk, 0);
        byte[] second = ChunkEncoder.encodeSectionBlob(chunk, 0);
        assertSame(first, second);
        assertArrayEquals(first, ChunkEncoder.encodeSectionBlob(chunk.getSection(0)));
    }

    @Test
    void testSetBlockStateInvalidatesSectionBlob() {
        var chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD);
        byte[] before = ChunkEncoder.encodeSectionBlob(chunk, 0);
        chunk.setBlockState(0, 0, 0, STONE.getDefaultState(), 0, false);
        byte[] after = ChunkEncoder.encodeSectionBlob(chunk, 0);
        assertNotSame(before, after);
        assertFalse(Arrays.equals(before, after));
    }

    @Test
    void testBiomesBlobCachedAndInvalidated() {
        var chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD);
        byte[] first = ChunkEncoder.encodeBiomesBlob(chunk);
        byte[] second = ChunkEncoder.encodeBiomesBlob(chunk);
        assertSame(first, second);
        chunk.setBiome(0, 0, 0, BiomeTypes.CHERRY_GROVE);
        byte[] third = ChunkEncoder.encodeBiomesBlob(chunk);
        assertNotSame(second, third);
        assertFalse(Arrays.equals(second, third));
    }

    @Test
    void testCacheRespectsSizeBound() {
        var chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD);
        var cache = chunk.getNetworkCache();
        int sectionCount = chunk.getDimensionType().chunkSectionCount();
        int minSectionY = chunk.getDimensionType().minSectionY();
        for (int i = 0; i < sectionCount; i++) {
            ChunkEncoder.encodeSectionBlob(chunk, minSectionY + i);
        }
        assertEquals(sectionCount, cache.cachedSectionEntryCount());
        chunk.setBlockState(0, 0, 0, STONE.getDefaultState(), 0, false);
        ChunkEncoder.encodeSectionBlob(chunk, 0);
        assertEquals(sectionCount, cache.cachedSectionEntryCount());
    }

    @Test
    void testPaletteSnapshotSingleSlot() {
        var section = new AllayChunkSection((byte) 0);
        var layer0 = section.blockLayers()[0];
        assertNull(layer0.getCachedNetworkBytes());
        byte[] blob1 = ChunkEncoder.encodeSectionBlob(section);
        byte[] cached = layer0.getCachedNetworkBytes();
        assertNotNull(cached);
        assertSame(cached, layer0.getCachedNetworkBytes());
        section.setBlockState(0, 0, 0, STONE.getDefaultState(), 0);
        assertNull(layer0.getCachedNetworkBytes());
        byte[] blob2 = ChunkEncoder.encodeSectionBlob(section);
        assertFalse(Arrays.equals(blob1, blob2));
    }
}