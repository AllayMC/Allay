package org.allaymc.api.world.chunk;

import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.biome.BiomeType;
import org.jetbrains.annotations.Range;

/**
 * {@code ChunkSection} provides
 * block layers and biomes for one sixteen-by-sixteen-by-sixteen
 * section of a chunk. All coordinates are local to the section, and access is
 * not thread-safe; callers sharing a section must use the owning chunk's
 * appropriate locking operation.
 *
 * @author daoge_cmd
 */
@NotThreadSafe
public interface ChunkSection {

    /**
     * Returns the block state in the requested layer at section-local
     * coordinates from zero through fifteen. The caller must hold any required
     * block read lock while accessing a shared section.
     */
    BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, int layer);

    /**
     * Replaces the block state in the requested layer at section-local
     * coordinates from zero through fifteen. The caller must hold any required
     * block write lock while modifying a shared section.
     */
    void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer);

    /**
     * Returns the biome at section-local coordinates from zero through fifteen
     * on every axis. The caller must hold any required biome read lock while
     * accessing a shared section.
     */
    BiomeType getBiomeType(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z);

    /**
     * Replaces the biome at section-local coordinates from zero through fifteen
     * on every axis. The caller must hold any required biome write lock while
     * modifying a shared section.
     */
    void setBiomeType(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    /**
     * Returns whether every block in layer zero is air. Other block layers do
     * not affect this classification, and callers must synchronize access when
     * the section can change concurrently.
     */
    boolean isAirSection();
}
