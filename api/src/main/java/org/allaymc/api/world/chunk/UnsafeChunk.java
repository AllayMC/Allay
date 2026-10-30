package org.allaymc.api.world.chunk;

import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.poi.PoiType;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * {@code UnsafeChunk} provides
 * unsynchronized access to one chunk's block sections, biomes, block
 * entities, and auxiliary state. X and Z positions within the chunk use zero
 * through fifteen, while block Y uses the dimension's absolute height range.
 * Callers sharing the chunk must establish appropriate synchronization or use
 * its safe wrapper; returned mutable objects remain subject to their own access
 * rules.
 *
 * @author Cool_Loong | daoge_cmd
 */
@NotThreadSafe
public interface UnsafeChunk {

    /**
     * Returns whether this chunk is currently loaded into its dimension.
     */
    boolean isLoaded();

    /**
     * Returns an unmodifiable view of the loaders currently attached to this
     * chunk. The loader objects remain shared, and membership changes can
     * become visible after the view is obtained.
     */
    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    /**
     * Invokes the consumer for each current loader on the calling thread. The
     * consumer receives shared loader objects, and callers remain responsible
     * for synchronization required by their own operations.
     */
    default void forEachChunkLoaders(Consumer<ChunkLoader> consumer) {
        getChunkLoaders().forEach(consumer);
    }

    /**
     * Queues a consumer for the chunk's next task-processing tick, using the
     * loaders present when it runs. This inherits {@link
     * #addChunkTask(Runnable)} scheduling behavior rather than capturing
     * current membership.
     */
    default void forEachChunkLoaderLater(Consumer<ChunkLoader> consumer) {
        addChunkTask(() -> forEachChunkLoaders(consumer));
    }

    /**
     * Attaches the supplied loader to this chunk's loader membership set.
     */
    void addChunkLoader(ChunkLoader chunkLoader);

    /**
     * Removes the supplied loader from this chunk's loader membership set.
     */
    void removeChunkLoader(ChunkLoader chunkLoader);

    /**
     * Returns the current number of loaders attached to this chunk.
     */
    default int getChunkLoaderCount() {
        return getChunkLoaders().size();
    }

    /**
     * Queues a task for execution in the next chunk tick. The task runs in the
     * chunk's processing context, so it must respect that context's access and
     * blocking restrictions.
     */
    void addChunkTask(Runnable task);

    /**
     * Returns the generation stage currently associated with this chunk.
     */
    ChunkState getState();

    /**
     * Returns the dimension type defining this chunk's vertical coordinate
     * bounds.
     */
    DimensionType getDimensionType();

    /**
     * Returns the chunk X coordinate, measured in sixteen-block columns.
     */
    int getX();

    /**
     * Returns the chunk Z coordinate, measured in sixteen-block columns.
     */
    int getZ();

    /**
     * Removes and returns the block entity at local X/Z and absolute block Y,
     * or {@code null} when absent. Invalid coordinates throw {@link
     * IllegalArgumentException}; the returned entity is not a copy.
     */
    BlockEntity removeBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Returns the shared block entity at local X/Z and absolute block Y, or
     * {@code null} when absent. Invalid coordinates throw {@link
     * IllegalArgumentException} rather than returning an out-of-height default.
     */
    BlockEntity getBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Returns an unmodifiable view of block entities keyed by {@link
     * HashUtils#hashChunkXYZ(int, int, int)}. Values remain shared entities,
     * and the view does not make operations on those entities thread-safe.
     */
    @UnmodifiableView
    Map<Integer, BlockEntity> getBlockEntities();

    /**
     * Adds the supplied block entity using its position within this chunk. The
     * entity remains shared; callers must ensure that its dimension and chunk
     * coordinates match the owning chunk.
     */
    void addBlockEntity(BlockEntity blockEntity);

    /**
     * Returns block entities belonging to the requested absolute section Y
     * coordinate. The collection is unmodifiable but contains shared entities;
     * an out-of-range section coordinate throws {@link
     * IllegalArgumentException}.
     */
    @UnmodifiableView
    Collection<BlockEntity> getSectionBlockEntities(int sectionY);

    /**
     * Schedules an update at local X/Z and absolute block Y for an absolute
     * world tick. Invalid coordinates throw {@link IllegalArgumentException};
     * this deadline is not a delay measured from submission.
     */
    void addScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, long time);

    /**
     * Returns whether an update is currently scheduled at local X/Z and
     * absolute block Y. Invalid coordinates throw {@link
     * IllegalArgumentException}; the query does not execute or remove pending
     * work.
     */
    boolean hasScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Replaces a block state at local X/Z and absolute block Y in the selected
     * layer. Invalid coordinates throw {@link IllegalArgumentException}; {@code
     * send} requests propagation of the change to chunk viewers.
     */
    void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer, boolean send);

    /**
     * Replaces the selected block layer and requests viewer notification for
     * the change. This delegates to the full mutation method with {@code send}
     * enabled and inherits its coordinate requirements.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean)
     */
    default void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer) {
        setBlockState(x, y, z, blockState, layer, true);
    }

    /**
     * Replaces the primary block layer and requests viewer notification for the
     * change. This delegates with layer zero and {@code send} enabled,
     * inheriting the full mutation method's coordinate requirements.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean)
     */
    default void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    /**
     * Returns the selected layer's block state at local X/Z and absolute block
     * Y. Heights outside the dimension return air; valid heights require X and
     * Z within zero through fifteen.
     */
    BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int layer);

    /**
     * Returns the primary-layer block state at local X/Z and absolute block Y.
     * This delegates to the layer-aware lookup with layer zero, including its
     * out-of-height air result and coordinate rules.
     *
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Returns the highest non-air block Y in the requested local horizontal
     * column. Empty columns return the dimension minimum, and X or Z outside
     * zero through fifteen throws {@link IllegalArgumentException}.
     */
    short getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z);

    /**
     * Replaces the biome at local X/Z and absolute block Y. All coordinates
     * must belong to this chunk's dimension range; invalid positions throw
     * {@link IllegalArgumentException} rather than changing a default biome.
     */
    void setBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    /**
     * Returns the biome at local X/Z and absolute block Y, using plains outside
     * the dimension's height range. At valid heights, out-of-range local X or Z
     * throws {@link IllegalArgumentException}.
     */
    BiomeType getBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Returns the shared section, never {@code null}, at an absolute section Y
     * coordinate.
     * Out-of-range sections throw {@link IllegalArgumentException}; the
     * returned section remains mutable and requires its own synchronization
     * when shared.
     */
    ChunkSection getSection(int sectionY);

    /**
     * Returns an unmodifiable list of sections across the chunk's vertical
     * range. Entries are shared mutable sections, so the collection restriction
     * does not make section operations safe for concurrent access.
     */
    @UnmodifiableView
    List<ChunkSection> getSections();

    /**
     * Returns the packed X/Z chunk-coordinate hash used by chunk lookup.
     */
    default long computeChunkHash() {
        return HashUtils.hashXZ(getX(), getZ());
    }

    /**
     * Returns registered point-of-interest entries keyed by {@link
     * HashUtils#hashChunkXYZ(int, int, int)} using local X/Z and absolute Y.
     * Implementations define the map's mutability; use the position-based
     * methods for portable chunk updates.
     */
    Map<Integer, PoiType> getPoiEntries();

    /**
     * Registers a point-of-interest type at local X/Z and absolute block Y.
     * Coordinates must lie within the chunk and dimension bounds; registering a
     * type replaces any entry at that position.
     */
    void addPoi(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, PoiType type);

    /**
     * Removes the point-of-interest entry at local X/Z and absolute block Y if
     * one exists. Coordinates must remain within the chunk and dimension bounds
     * even when the entry is absent.
     */
    void removePoi(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Returns the point-of-interest type at local X/Z and absolute block Y, or
     * {@code null} when absent. Coordinates must lie within the chunk and
     * dimension bounds for the lookup.
     */
    PoiType getPoi(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Returns a safe chunk wrapper over this chunk's shared data. The wrapper
     * coordinates its supported operations, but continuing to access the unsafe
     * instance directly still requires compatible caller synchronization.
     */
    Chunk toSafeChunk();
}
