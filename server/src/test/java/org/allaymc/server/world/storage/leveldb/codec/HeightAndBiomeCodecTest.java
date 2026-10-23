package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.chunk.AllayChunkBuilder;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code HeightAndBiomeCodecTest} checks
 * height offsets and biome identities across vertically separated chunk
 * sections.
 */
@ExtendWith(AllayTestExtension.class)
class HeightAndBiomeCodecTest {

    /**
     * Encodes custom heights and biomes located in different overworld
     * sections. Decoding into prepared sections must restore absolute heights
     * and associate each sampled biome with its original vertical section.
     */
    @Test
    void testSerializeAndDeserialize() {
        var chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD);

        chunk.setHeight(0, 0, (short) 100);
        chunk.setHeight(5, 5, (short) 200);
        chunk.setHeight(15, 15, (short) 319);

        chunk.setBiome(0, 0, 0, BiomeTypes.FOREST);
        chunk.setBiome(5, 64, 5, BiomeTypes.DESERT);

        byte[] data = HeightAndBiomeCodec.serialize(chunk);

        var builder = new AllayChunkBuilder()
                .chunkX(0)
                .chunkZ(0)
                .dimensionType(DimensionTypes.OVERWORLD);

        // The decoder writes into preexisting section palettes; allocate every section
        // first so vertical palette records are consumed in their expected order.
        var sections = new org.allaymc.server.world.chunk.AllayChunkSection[DimensionTypes.OVERWORLD.chunkSectionCount()];
        for (int i = 0; i < sections.length; i++) {
            sections[i] = new org.allaymc.server.world.chunk.AllayChunkSection((byte) (i + DimensionTypes.OVERWORLD.minSectionY()));
        }
        builder.sections(sections);

        HeightAndBiomeCodec.deserialize(data, builder);

        assertEquals(100, builder.getHeightMap().get(0, 0));
        assertEquals(200, builder.getHeightMap().get(5, 5));
        assertEquals(319, builder.getHeightMap().get(15, 15));

        // Absolute block Y zero belongs to section zero, offset to array index four.
        var sectionForY0 = sections[0 - DimensionTypes.OVERWORLD.minSectionY()];
        assertEquals(BiomeTypes.FOREST, sectionForY0.getBiomeType(0, 0, 0));

        // Absolute block Y sixty-four belongs to section four, offset to array index eight.
        var sectionForY64 = sections[64 / 16 - DimensionTypes.OVERWORLD.minSectionY()];
        assertEquals(BiomeTypes.DESERT, sectionForY64.getBiomeType(5, 0, 5));
    }
}
