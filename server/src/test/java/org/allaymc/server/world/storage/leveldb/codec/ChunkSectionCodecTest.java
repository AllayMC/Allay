package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.block.type.BlockTypes.OAK_WOOD;
import static org.allaymc.api.block.type.BlockTypes.STONE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ChunkSectionCodecTest} checks
 * section palette round trips, unsupported versions, and empty-section
 * initialization.
 */
@ExtendWith(AllayTestExtension.class)
class ChunkSectionCodecTest {

    /**
     * Stores distinct block states in both supported section layers before
     * encoding. Decoding must preserve the sampled positions and their layer
     * identities rather than merging the palettes.
     */
    @Test
    void testSerializeAndDeserialize() {
        var section = new AllayChunkSection((byte) 0);

        section.setBlockState(0, 0, 0, OAK_WOOD.getDefaultState(), 0);
        section.setBlockState(1, 1, 1, STONE.getDefaultState(), 0);

        section.setBlockState(2, 2, 2, OAK_WOOD.getDefaultState(), 1);

        byte[] data = ChunkSectionCodec.serialize(section, 0);
        assertNotNull(data);

        AllayChunkSection deserialized = ChunkSectionCodec.deserialize(data, 0, 0, 0);
        assertNotNull(deserialized);

        assertEquals(OAK_WOOD.getDefaultState(), deserialized.getBlockState(0, 0, 0, 0));
        assertEquals(STONE.getDefaultState(), deserialized.getBlockState(1, 1, 1, 0));
        assertEquals(OAK_WOOD.getDefaultState(), deserialized.getBlockState(2, 2, 2, 1));
    }

    /**
     * Supplies a section record whose leading version byte has no supported
     * decoder. The codec must return {@code null} so its storage caller can handle that
     * unsupported section.
     */
    @Test
    void testDeserializeUnknownVersionReturnsNull() {
        // Version 99 selects the unsupported-header path before any palette payload is
        // decoded, isolating fallback behavior from malformed-palette handling.
        byte[] data = new byte[]{99, 0, 0, 0};
        AllayChunkSection result = ChunkSectionCodec.deserialize(data, 0, 0, 0);
        assertNull(result);
    }

    /**
     * Starts with a section array containing no allocated section objects.
     * Filling the array must create every section with its coordinate offset
     * from the dimension's minimum section height.
     */
    @Test
    void testFillNullSections() {
        var dimensionType = DimensionTypes.OVERWORLD;
        var sections = new AllayChunkSection[dimensionType.chunkSectionCount()];
        assertNull(sections[0]);

        ChunkSectionCodec.fillNullSections(sections, dimensionType);

        for (int i = 0; i < sections.length; i++) {
            assertNotNull(sections[i], "Section at index " + i + " should not be null after fill");
            assertEquals((byte) (i + dimensionType.minSectionY()), sections[i].sectionY());
        }
    }
}
