package org.allaymc.server.world.chunk;

import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.ChunkSection;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.world.storage.leveldb.data.ChunkSectionVersion;

import static org.allaymc.api.block.type.BlockTypes.AIR;
import static org.allaymc.api.utils.hash.HashUtils.hashChunkSectionXYZ;

/**
 * {@code AllayChunkSection} stores
 * the block palettes and biome palette for one absolute section height
 * in a chunk. Palettes and their containing array are shared mutable objects;
 * record accessors expose them directly, so callers must coordinate access
 * through the owning chunk when sharing sections.
 *
 * @param sectionY absolute section Y coordinate
 * @param blockLayers {@code blockLayers} retains the supplied array and palettes.
 *                    Codecs and generation code receive them without copying.
 * @param biomes {@code biomes} retains the supplied mutable palette. Callers must
 *               coordinate shared access through the owning chunk biome locks.
 *
 * @author Cool_Loong | daoge_cmd
 */
@NotThreadSafe
public record AllayChunkSection(
        byte sectionY,
        Palette<BlockState>[] blockLayers,
        Palette<BiomeType> biomes
) implements ChunkSection {
    /**
     * {@code LAYER_COUNT} advertises two block layers in network section
     * headers. The default constructor also allocates two independent block
     * palettes.
     */
    public static final int LAYER_COUNT = 2;
    /**
     * {@code CURRENT_CHUNK_SECTION_VERSION} selects
     * the section format that includes an absolute section offset in
     * network and storage data.
     */
    public static final int CURRENT_CHUNK_SECTION_VERSION = ChunkSectionVersion.PALETTED_MULTI_WITH_OFFSET.ordinal();

    /**
     * Creates a section with independent air palettes for both block layers and
     * a plains biome palette. The absolute section coordinate is retained for
     * encoding and chunk indexing.
     */
    @SuppressWarnings("unchecked")
    public AllayChunkSection(byte sectionY) {
        this(sectionY, new Palette[]{new Palette<>(AIR.getDefaultState()), new Palette<>(AIR.getDefaultState())}, new Palette<>(BiomeTypes.PLAINS));
    }

    /**
     * Creates a section retaining the supplied block-layer array and allocating
     * a plains biome palette. The array and its palettes are shared without
     * validation or defensive copies.
     */
    public AllayChunkSection(byte sectionY, Palette<BlockState>[] blockLayer) {
        this(sectionY, blockLayer, new Palette<>(BiomeTypes.PLAINS));
    }

    /**
     * Returns the selected block palette value for section-local coordinates.
     * Coordinates and the layer index must already be valid, and shared access
     * requires the owning chunk's block read lock.
     */
    @Override
    public BlockState getBlockState(int x, int y, int z, int layer) {
        return blockLayers[layer].get(hashChunkSectionXYZ(x, y, z));
    }

    /**
     * Updates the selected block palette at section-local coordinates and marks
     * that palette dirty. Callers must supply valid coordinates and a layer
     * index while holding any required block write lock.
     */
    @Override
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        blockLayers[layer].set(hashChunkSectionXYZ(x, y, z), blockState);
    }

    /**
     * Returns the biome palette value for section-local coordinates from zero
     * through fifteen. This method performs no coordinate validation, and
     * shared access requires the owning chunk's biome read lock.
     */
    @Override
    public BiomeType getBiomeType(int x, int y, int z) {
        return biomes.get(hashChunkSectionXYZ(x, y, z));
    }

    /**
     * Updates the biome palette at section-local coordinates and marks the
     * palette dirty. Callers must supply valid coordinates and hold the owning
     * chunk's biome write lock for shared access.
     */
    @Override
    public void setBiomeType(int x, int y, int z, BiomeType biomeType) {
        biomes.set(hashChunkSectionXYZ(x, y, z), biomeType);
    }

    /**
     * Returns whether layer zero uses only its first palette entry. This
     * implementation does not verify that the entry is air, so a uniform
     * non-air section can also return {@code true}; it does not fully implement
     * the interface's air-only contract.
     */
    @Override
    public boolean isAirSection() {
        return blockLayers[0].oneEntryOnly();
    }

    /**
     * Returns whether at least one block palette is marked dirty for
     * persistence. Biome dirtiness is excluded, and this inspection neither
     * clears flags nor establishes synchronization with palette writers.
     */
    public boolean hasDirtyBlockLayer() {
        for (var layer : blockLayers) {
            if (layer.isDirty()) {
                return true;
            }
        }

        return false;
    }
}
