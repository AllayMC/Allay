package org.allaymc.api.world.generator.context;

import lombok.Getter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * {@code OtherChunkAccessibleContext} routes generation-time block access
 * between the current unsafe chunk and an externally bounded chunk source.
 * Callers use dimension coordinates; current-chunk access converts them to
 * local coordinates, while neighbor access selects a chunk before applying the
 * same local conversion.
 *
 * <p>
 * The current-chunk fast path also requires an in-range Y coordinate. Other
 * positions therefore pass through the supplied source, even when their
 * horizontal coordinates match the current chunk. Missing or inaccessible
 * neighboring chunks produce air for block reads, {@code null} for block-entity
 * reads, and ignored writes. These defaults let bounded generation inspect
 * outside its available region without loading additional chunks, but they
 * cannot distinguish absent terrain from an access restriction.
 *
 * @author daoge_cmd
 */
public abstract class OtherChunkAccessibleContext extends Context {
    /**
     * {@code AIR} supplies the block-read fallback when the bounded neighboring
     * source cannot expose a requested chunk.
     */
    private static final BlockState AIR = BlockTypes.AIR.getDefaultState();

    /**
     * {@code chunkSource} controls neighbor accessibility. Missing chunks use read
     * defaults or ignored writes instead of triggering additional chunk loads.
     */
    @Getter
    protected ChunkSource chunkSource;

    /**
     * Creates a context retaining the current unsafe chunk and neighboring
     * source. The caller must establish the allowed region and synchronized
     * lifetime; this constructor neither reserves chunks nor verifies source
     * bounds.
     */
    public OtherChunkAccessibleContext(UnsafeChunk currentChunk, ChunkSource chunkSource) {
        super(currentChunk);
        this.chunkSource = chunkSource;
    }

    /**
     * Replaces the primary block layer at dimension coordinates using the
     * current or accessible neighboring chunk. This overload selects layer
     * zero; writes outside the source's available region are ignored.
     */
    public void setBlockState(int x, int y, int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    /**
     * Replaces the selected block layer at dimension coordinates, using the
     * unsafe current chunk only when all coordinates belong to it. Other
     * positions go through the supplied source, and inaccessible chunks
     * silently discard the write.
     */
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        if (isInCurrentChunk(x, y, z)) {
            // Masking removes the validated chunk origin while retaining local X/Z.
            currentChunk.setBlockState(x & 15, y, z & 15, blockState, layer);
            return;
        }

        setBlockStateInOtherChunk(x, y, z, blockState, layer);
    }

    /**
     * Returns the primary block-layer state at dimension coordinates, using air
     * when the neighboring source has no accessible chunk. This overload
     * selects layer zero and inherits the source's access restrictions.
     */
    public BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Returns the selected block-layer state at dimension coordinates through
     * the current chunk or bounded source. Missing neighboring chunks return
     * air; valid chunk lookups retain that chunk's own height and layer rules.
     */
    public BlockState getBlockState(int x, int y, int z, int layer) {
        // The current-chunk branch masks dimension X/Z into local block coordinates.
        return isInCurrentChunk(x, y, z) ?
                currentChunk.getBlockState(x & 15, y, z & 15, layer) :
                getBlockStateInOtherChunk(x, y, z, layer);
    }

    /**
     * Returns a shared block entity at dimension coordinates, or {@code null} when
     * absent or the neighboring chunk is inaccessible. Available chunk lookups
     * still validate height, so out-of-range Y can throw rather than returning
     * {@code null}.
     */
    public BlockEntity getBlockEntity(int x, int y, int z) {
        // The current-chunk branch masks dimension X/Z into local block coordinates.
        return isInCurrentChunk(x, y, z) ?
                currentChunk.getBlockEntity(x & 15, y, z & 15) :
                getBlockEntityInOtherChunk(x, y, z);
    }

    /**
     * Writes the selected layer through the neighboring source using dimension
     * coordinates. A missing chunk discards the request; an available chunk
     * receives local horizontal coordinates and applies its normal mutation
     * validation.
     */
    private void setBlockStateInOtherChunk(int x, int y, int z, BlockState blockState, int layer) {
        // Arithmetic shifts select containing chunks; masking below preserves
        // their local horizontal positions even when dimension coordinates are negative.
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        if (chunk == null) {
            // No accessible neighboring chunk means this write cannot be
            // applied within the reserved region; it is dropped without
            // requesting additional generation work.
            return;
        }

        chunk.setBlockState(x & 15, y, z & 15, blockState, layer);
    }

    /**
     * Reads the selected layer through the neighboring source using dimension
     * coordinates. A missing chunk returns the retained air state; an available
     * chunk receives local horizontal coordinates and applies its normal height
     * fallback.
     */
    private BlockState getBlockStateInOtherChunk(int x, int y, int z, int layer) {
        // Arithmetic shifts select containing chunks; masking below preserves
        // their local horizontal positions even when dimension coordinates are negative.
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        // An unavailable neighboring chunk is represented as air for generation
        // reads, so callers cannot distinguish actual air from terrain outside
        // the accessible region.
        return chunk == null ? AIR : chunk.getBlockState(x & 15, y, z & 15, layer);
    }

    /**
     * Reads a shared block entity through the neighboring source using
     * dimension coordinates. A missing chunk returns {@code null}, while an available
     * chunk receives local horizontal coordinates and applies its normal
     * coordinate validation.
     */
    private BlockEntity getBlockEntityInOtherChunk(int x, int y, int z) {
        // Arithmetic shifts select containing chunks; masking below preserves
        // their local horizontal positions even when dimension coordinates are negative.
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        // No accessible chunk supplies an entity map, so this fallback returns
        // the same null result used for an available chunk with no entity.
        return chunk == null ? null : chunk.getBlockEntity(x & 15, y, z & 15);
    }

    /**
     * Returns whether dimension coordinates lie inside the current chunk's
     * horizontal column and vertical dimension range. The test includes Y
     * because the direct unsafe route must have a valid mutation position; an
     * out-of-height coordinate falls through to the other-chunk route instead.
     * Horizontal bounds are half-open sixteen-block intervals, while the
     * dimension height endpoints are inclusive. This method selects the access
     * route without loading or reserving another chunk.
     */
    private boolean isInCurrentChunk(int x, int y, int z) {
        var currentChunkX = currentChunk.getX();
        var currentChunkZ = currentChunk.getZ();
        var dimType = currentChunk.getDimensionType();
        return
                x >= currentChunkX * 16 && x < currentChunkX * 16 + 16 &&
                z >= currentChunkZ * 16 && z < currentChunkZ * 16 + 16 &&
                y >= dimType.getMinHeight() && y <= dimType.getMaxHeight();
    }
}
