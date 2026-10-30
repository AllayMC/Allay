package org.allaymc.server.world.storage.leveldb.codec;

import io.netty.buffer.Unpooled;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.world.chunk.AllayChunkBuilder;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.HeightMap;
import org.allaymc.server.world.storage.leveldb.LevelDBUtils;

/**
 * {@code HeightAndBiomeCodec} converts
 * persistent height maps and biome palettes into chunk data across
 * current three-dimensional and legacy two-dimensional layouts. Current height
 * values are stored relative to the dimension minimum, while the runtime height
 * map contains absolute block heights. The decoder reverses that offset before
 * assigning the resulting height map.
 *
 * <p>
 * Current biome palettes are read in vertical section order and may reuse the
 * preceding decoded palette. The writer deliberately supplies no preceding
 * palette, so it emits self-contained sections. Legacy biome bytes describe
 * horizontal columns and are expanded through each available section; both
 * decoders rely on the caller to supply section objects before loading biome
 * values.
 *
 * @see <a href="https://github.com/bedrock-dev/bedrock-level/blob/main/src/include/data_3d.h#L115">Biome 3d</a>
 */
@Slf4j
@UtilityClass
public final class HeightAndBiomeCodec {

    /**
     * {@code HEIGHTMAP_SIZE} counts
     * the 256 horizontal columns whose heights precede biome data in
     * each chunk record.
     */
    public static final int HEIGHTMAP_SIZE = 256;

    /**
     * Encodes the chunk's height map and section biome palettes in {@code DATA_3D}
     * format. Heights are adjusted by the dimension minimum and written as
     * little-endian unsigned-short bit patterns; palette compaction mutates the
     * supplied sections. Callers must supply exactly {@code HEIGHTMAP_SIZE}
     * heights because the decoder consumes a fixed-size height prefix before
     * reading biome palettes.
     *
     * <p>
     * Each section is encoded independently because the preceding-palette
     * argument remains {@code null}. This avoids emitting palette-copy flags, while the
     * decoder still accepts them from existing data. Callers must coordinate
     * access to the unsafe chunk during height calculation and palette
     * compaction; the returned array is detached from the temporary output
     * buffer.
     */
    public static byte[] serialize(AllayUnsafeChunk chunk) {
        return LevelDBUtils.withByteBufToArray(heightAndBiomesBuffer -> {
            var dimensionType = chunk.getDimensionType();
            // Stored heights use the dimension bottom as zero; the decoder restores
            // that origin after consuming exactly HEIGHTMAP_SIZE values. This loop
            // writes the entire retained array, so extra heights would be mistaken
            // for biome palette bytes and break section alignment.
            for (var height : chunk.calculateAndGetHeightMap().getHeights()) {
                heightAndBiomesBuffer.writeShortLE(height - dimensionType.getMinHeight());
            }

            // Serialize biomes
            Palette<BiomeType> lastPalette = null;
            for (int y = dimensionType.minSectionY(); y <= dimensionType.maxSectionY(); y++) {
                AllayChunkSection section = chunk.getSection(y);
                section.biomes().compact();
                section.biomes().writeToStorage(heightAndBiomesBuffer, BiomeType::getId, lastPalette);
                // TODO: Verify biome-copy compatibility with stored-world consumers before
                // enabling previous-palette reuse; this writer currently emits every palette.
            }
        });
    }

    /**
     * Loads {@code DATA_3D} heights and biome palettes into the supplied builder. The
     * dimension and section array must already be present, with section entries
     * corresponding to increasing section coordinates from the dimension
     * minimum.
     *
     * <p>
     * Unsigned stored heights are shifted back to absolute block heights before
     * the new height map is assigned. Each decoded biome palette becomes the
     * preceding palette for subsequent copy references. {@code null} section entries
     * are skipped without consuming palette bytes, so callers requiring
     * complete alignment must fill those entries before invoking this decoder.
     */
    public static void deserialize(byte[] data, AllayChunkBuilder builder) {
        var heightAndBiomesBuffer = Unpooled.wrappedBuffer(data);
        var dimensionType = builder.getDimensionType();

        // Height map
        short[] heights = new short[HEIGHTMAP_SIZE];
        for (int i = 0; i < HEIGHTMAP_SIZE; i++) {
            // DATA_3D heights are offsets from the dimension bottom, so restore
            // the absolute height before storing the runtime short value.
            heights[i] = (short) (heightAndBiomesBuffer.readUnsignedShortLE() + dimensionType.getMinHeight());
        }
        builder.heightMap(new HeightMap(heights));

        // Biomes
        Palette<BiomeType> lastPalette = null;
        var minSectionY = dimensionType.minSectionY();
        for (int y = minSectionY; y <= dimensionType.maxSectionY(); y++) {
            AllayChunkSection section = builder.getSections()[y - minSectionY];
            if (section == null) {
                continue;
            }

            // Copy flags refer to the preceding decoded section, so carry that
            // palette forward only after this section has consumed its encoded
            // data.
            section.biomes().readFromStorage(heightAndBiomesBuffer, HeightAndBiomeCodec::getBiomeByIdNonNull, lastPalette);
            lastPalette = section.biomes();
        }
    }

    /**
     * Loads legacy {@code DATA_2D} heights and expands each horizontal biome column
     * through the supplied sections. Legacy height values are read as signed
     * shorts without adding the dimension minimum.
     *
     * <p>
     * The 256 biome bytes use an X-plus-sixteen-times-Z index, whereas section
     * palettes use the chunk-section position hash. The decoder applies that
     * conversion at every local height to preserve column identity. Input bytes
     * are passed to registry lookup as signed values; missing registry entries
     * can therefore remain {@code null} rather than taking the exception-based plains
     * fallback.
     */
    public static void deserializeOld(byte[] data, AllayChunkBuilder builder) {
        var heightAndBiomesBuffer = Unpooled.wrappedBuffer(data);
        short[] heights = new short[HEIGHTMAP_SIZE];
        for (int i = 0; i < HEIGHTMAP_SIZE; i++) {
            heights[i] = heightAndBiomesBuffer.readShortLE();
        }
        builder.heightMap(new HeightMap(heights));

        byte[] biomes = new byte[HEIGHTMAP_SIZE];
        heightAndBiomesBuffer.readBytes(biomes);

        var dimensionType = builder.getDimensionType();
        var minSectionY = dimensionType.minSectionY();
        for (int y = minSectionY; y <= dimensionType.maxSectionY(); y++) {
            var section = builder.getSections()[y - minSectionY];
            if (section == null) {
                continue;
            }

            var biomePalette = section.biomes();
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int sy = 0; sy < 16; sy++) {
                        // The legacy array indexes horizontal columns as X plus
                        // sixteen times Z; the section hash also includes the
                        // repeated local height.
                        biomePalette.set(HashUtils.hashChunkSectionXYZ(x, sy, z), getBiomeByIdNonNull(biomes[x + 16 * z]));
                    }
                }
            }
        }
    }

    /**
     * Looks up the runtime biome for the supplied numeric identifier. Only an
     * array-bounds exception triggers the logged plains fallback; a registry
     * lookup returning {@code null} passes through unchanged despite this method's
     * name.
     */
    public static BiomeType getBiomeByIdNonNull(int id) {
        try {
            return Registries.BIOMES.getByK1(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Only an out-of-bounds registry failure reaches this fallback;
            // plain missing-key null results remain unchanged and must be
            // handled by downstream consumers.
            log.warn("Unknown biome id: {}", id);
            return BiomeTypes.PLAINS;
        }
    }
}
