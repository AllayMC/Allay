package org.allaymc.server.world.chunk;

import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.*;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.poi.PoiType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.function.Consumer;

/**
 * {@code AllayChunk} wraps
 * an unsafe chunk with separate per-section block and biome locks.
 * Individual accesses protect the selected section, and callback operations
 * acquire block locks before biome locks across either one section or the
 * entire column. {@code blockLocks} and {@code biomeLocks} protect their
 * respective palette categories; operations needing both acquire
 * {@code blockLocks} first. Callback protection ends when the callback returns.
 *
 * <p>
 * The wrapper and unsafe view share the same mutable chunk data; returning a
 * section or collection does not extend a lock beyond the call. Lombok
 * delegates members selected by {@code ThreadSafeMethods} directly, relying on
 * their underlying storage. The public interface requires thread-safe calls,
 * but the current height cache still mutates under shared block read locks and
 * shares dirty flags across section locks. Those implementation limitations
 * require care when reasoning about concurrent height calculations and writes.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayChunk implements Chunk {

    /**
     * {@code unsafeChunk} retains the shared backing chunk. Selected members
     * delegate directly while block and biome access use this wrapper's locks.
     */
    @Delegate(types = ThreadSafeMethods.class)
    protected final AllayUnsafeChunk unsafeChunk;

    /**
     * Per-section block palette locks.
     */
    protected final ChunkSectionLocks blockLocks;
    /**
     * Per-section biome palette locks.
     */
    protected final ChunkSectionLocks biomeLocks;

    /**
     * Creates a wrapper sharing the supplied unsafe chunk and allocating
     * independent block and biome lock sets. Callers must use this same wrapper
     * for coordinated access rather than constructing competing lock domains.
     */
    AllayChunk(AllayUnsafeChunk unsafeChunk) {
        this.unsafeChunk = unsafeChunk;

        var dimensionType = unsafeChunk.getDimensionType();
        this.blockLocks = new ChunkSectionLocks(dimensionType);
        this.biomeLocks = new ChunkSectionLocks(dimensionType);
    }

    /**
     * Returns the height of a valid local column while holding block read locks
     * for every section. The backing lookup may update its height cache despite
     * the shared locks, so this implementation does not exclusively serialize
     * cache writers.
     */
    @Override
    public short getHeight(int x, int z) {
        AllayUnsafeChunk.checkXZ(x, z);
        short[] height = new short[1];
        applyOperation(chunk -> height[0] = chunk.getHeight(x, z), OperationType.READ, OperationType.NONE);
        return height[0];
    }

    /**
     * Returns the selected layer under the containing section's block read
     * lock. Out-of-height Y returns air before horizontal validation; otherwise
     * X and Z must be valid local coordinates.
     */
    @Override
    public BlockState getBlockState(int x, int y, int z, int layer) {
        if (y < getDimensionType().getMinHeight() || y > getDimensionType().getMaxHeight()) {
            return BlockTypes.AIR.getDefaultState();
        }

        AllayUnsafeChunk.checkXZ(x, z);
        // Arithmetic shifting selects the containing section even for negative heights.
        var sectionY = y >> 4;
        blockLocks.lockReadLockAt(sectionY);
        try {
            return unsafeChunk.getBlockState(x, y, z, layer);
        } finally {
            blockLocks.unlockReadLockAt(sectionY);
        }
    }

    /**
     * Updates a valid local block position under the containing section's block
     * write lock. The backing mutation also invalidates height data, invokes
     * the block-change callback, and optionally queues viewer updates.
     */
    @Override
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send) {
        unsafeChunk.checkXYZ(x, y, z);

        // Arithmetic shifting selects the containing section even for negative heights.
        var sectionY = y >> 4;
        blockLocks.lockWriteLockAt(sectionY);
        try {
            unsafeChunk.setBlockState(x, y, z, blockState, layer, send);
        } finally {
            blockLocks.unlockWriteLockAt(sectionY);
        }
    }

    /**
     * Returns the biome under the containing section's biome read lock.
     * Out-of-height Y returns plains before horizontal validation; otherwise X
     * and Z must be valid local coordinates.
     */
    @Override
    public BiomeType getBiome(int x, int y, int z) {
        if (y < getDimensionType().getMinHeight() || y > getDimensionType().getMaxHeight()) {
            return BiomeTypes.PLAINS;
        }

        AllayUnsafeChunk.checkXZ(x, z);
        // Arithmetic shifting selects the containing section even for negative heights.
        var sectionY = y >> 4;
        biomeLocks.lockReadLockAt(sectionY);
        try {
            return unsafeChunk.getBiome(x, y, z);
        } finally {
            biomeLocks.unlockReadLockAt(sectionY);
        }
    }

    /**
     * Updates a valid local block position under the containing section's biome
     * write lock. The lock is released when the backing palette mutation
     * completes or throws an exception.
     */
    @Override
    public void setBiome(int x, int y, int z, BiomeType biomeType) {
        unsafeChunk.checkXYZ(x, y, z);

        // Arithmetic shifting selects the containing section even for negative heights.
        var sectionY = y >> 4;
        biomeLocks.lockWriteLockAt(sectionY);
        try {
            unsafeChunk.setBiome(x, y, z, biomeType);
        } finally {
            biomeLocks.unlockWriteLockAt(sectionY);
        }
    }

    /**
     * Runs the callback after acquiring all requested block locks, followed by
     * all requested biome locks. {@code NONE} leaves that data category unprotected,
     * and the callback must not access it concurrently without another valid
     * synchronization arrangement. Locks are released after the callback even
     * when it throws. Callbacks must avoid read-to-write upgrades or acquiring
     * an earlier lock category while holding a later one, since reentrancy does
     * not prevent those deadlocks.
     */
    @Override
    public void applyOperation(Consumer<UnsafeChunk> operation, OperationType block, OperationType biome) {
        // Acquiring every block lock before any biome lock gives whole-chunk
        // callbacks a common ordering; nested callbacks must preserve that
        // order to avoid deadlock.
        tryLockAllSections(block, blockLocks);
        tryLockAllSections(biome, biomeLocks);
        try {
            operation.accept(unsafeChunk);
        } finally {
            tryUnlockAllSections(block, blockLocks);
            tryUnlockAllSections(biome, biomeLocks);
        }
    }

    /**
     * Runs the callback after validating the absolute section coordinate and
     * acquiring its requested block then biome locks. Only that section is
     * protected, so the callback must not use the borrowed view to infer
     * whole-chunk stability. {@code NONE} provides no protection for its category, and
     * read locks must not be upgraded to writes. Both selected locks are
     * released when the callback returns or throws.
     */
    @Override
    public void applyOperationInSection(int sectionY, Consumer<ChunkSection> operation, OperationType block, OperationType biome) {
        unsafeChunk.checkSectionY(sectionY);
        // Section callbacks use the same block-before-biome ordering as
        // whole-chunk callbacks, so callers cannot safely invert that order
        // through nested operations.
        tryLockSection(sectionY, block, blockLocks);
        tryLockSection(sectionY, biome, biomeLocks);
        try {
            operation.accept(unsafeChunk.getSection(sectionY));
        } finally {
            tryUnlockSection(sectionY, block, blockLocks);
            tryUnlockSection(sectionY, biome, biomeLocks);
        }
    }

    /**
     * Acquires the requested access mode across all supplied section locks, or
     * does nothing for {@code NONE}. The caller must release the matching mode on the
     * same thread after protected work.
     */
    protected void tryLockAllSections(OperationType operationType, ChunkSectionLocks locks) {
        switch (operationType) {
            case READ -> locks.lockAllReadLocks();
            case WRITE -> locks.lockAllWriteLocks();
        }
    }

    /**
     * Acquires the requested mode for one valid absolute section Y, or does
     * nothing for {@code NONE}. The caller must release the matching section and mode
     * on the acquiring thread.
     */
    protected void tryLockSection(int sectionY, OperationType operationType, ChunkSectionLocks locks) {
        switch (operationType) {
            case READ -> locks.lockReadLockAt(sectionY);
            case WRITE -> locks.lockWriteLockAt(sectionY);
        }
    }

    /**
     * Releases the requested mode across all supplied section locks, or does
     * nothing for {@code NONE}. The current thread must already own the corresponding
     * acquisitions in every section.
     */
    protected void tryUnlockAllSections(OperationType operationType, ChunkSectionLocks locks) {
        switch (operationType) {
            case READ -> locks.unlockAllReadLocks();
            case WRITE -> locks.unlockAllWriteLocks();
        }
    }

    /**
     * Releases the requested mode for one valid absolute section Y, or does
     * nothing for {@code NONE}. The current thread must own the corresponding
     * acquisition before invoking this method.
     */
    protected void tryUnlockSection(int sectionY, OperationType operationType, ChunkSectionLocks locks) {
        switch (operationType) {
            case READ -> locks.unlockReadLockAt(sectionY);
            case WRITE -> locks.unlockWriteLockAt(sectionY);
        }
    }

    /**
     * Acquires the requested side of one read-write lock, or does nothing for
     * {@code NONE}. The caller must release that same side on the acquiring thread
     * after protected work.
     */
    protected void tryLock(OperationType operationType, ReadWriteLock lock) {
        switch (operationType) {
            case READ -> lock.readLock().lock();
            case WRITE -> lock.writeLock().lock();
        }
    }

    /**
     * Releases the requested side of one read-write lock, or does nothing for
     * {@code NONE}. The current thread must own the matching acquisition before
     * requesting this release.
     */
    protected void tryUnlock(OperationType operationType, ReadWriteLock lock) {
        switch (operationType) {
            case READ -> lock.readLock().unlock();
            case WRITE -> lock.writeLock().unlock();
        }
    }

    /**
     * Returns the same backing unsafe chunk without acquiring or transferring
     * locks. The result shares all mutable data with this wrapper, and callers
     * must establish synchronization for subsequent access.
     */
    @Override
    public UnsafeChunk toUnsafeChunk() {
        return unsafeChunk;
    }

    /**
     * {@code ThreadSafeMethods} selects members Lombok delegates without palette
     * locks. Returned objects remain shared and keep their own synchronization
     * requirements.
     */
    private interface ThreadSafeMethods {
        /**
         * Returns the backing chunk's volatile load flag without palette
         * locking.
         */
        boolean isLoaded();

        /**
         * Returns the backing chunk's unmodifiable live loader view without
         * palette locks. Membership uses a concurrent set, while individual
         * loader objects retain their own access requirements.
         */
        Set<ChunkLoader> getChunkLoaders();

        /**
         * Adds loader membership through the backing chunk's concurrent set.
         */
        void addChunkLoader(ChunkLoader chunkLoader);

        /**
         * Removes loader membership through the backing chunk's concurrent set.
         */
        void removeChunkLoader(ChunkLoader chunkLoader);

        /**
         * Queues a task directly in the backing chunk's concurrent task queue.
         * The task runs later during chunk processing and is discarded if
         * processing finds no attached loaders.
         */
        void addChunkTask(Runnable task);

        /**
         * Returns the backing chunk's currently published generation state
         * without palette locking.
         */
        ChunkState getState();

        /**
         * Returns the shared dimension type associated with the backing
         * chunk.
         */
        DimensionType getDimensionType();

        /**
         * Returns the backing chunk's fixed X coordinate in chunk units.
         */
        int getX();

        /**
         * Returns the backing chunk's fixed Z coordinate in chunk units.
         */
        int getZ();

        /**
         * Removes a block entity through the backing concurrent position map
         * and returns it, or {@code null} when absent. Coordinates are validated, and
         * entity lifecycle operations remain the caller's responsibility.
         */
        BlockEntity removeBlockEntity(int x, int y, int z);

        /**
         * Returns the shared entity from the backing concurrent position map,
         * or {@code null} when absent. Coordinates are validated, but accessing the
         * returned entity receives no palette-lock protection.
         */
        BlockEntity getBlockEntity(int x, int y, int z);

        /**
         * Returns the backing unmodifiable live block-entity map without
         * palette locking. Keys are local position hashes, and operations on
         * the shared entity values require their own synchronization.
         */
        Map<Integer, BlockEntity> getBlockEntities();

        /**
         * Stores a shared block entity in the backing concurrent position map.
         * The caller must ensure that the entity belongs to this chunk because
         * insertion masks its horizontal coordinates.
         */
        void addBlockEntity(BlockEntity blockEntity);

        /**
         * Returns an unmodifiable snapshot of entity references matching the
         * absolute section Y. The backing chunk validates the section
         * coordinate; the entities themselves remain shared mutable objects.
         */
        Collection<BlockEntity> getSectionBlockEntities(int sectionY);

        /**
         * Replaces the scheduled update for a validated local position in the
         * backing concurrent map. The deadline is an absolute world tick, and
         * any earlier entry at that position is replaced.
         */
        void addScheduledUpdate(int x, int y, int z, long time);

        /**
         * Returns whether the backing concurrent schedule map contains a
         * validated local position. The result is an observation only and does
         * not reserve, execute, or remove the scheduled update.
         */
        boolean hasScheduledUpdate(int x, int y, int z);

        /**
         * Returns the backing unmodifiable live point-of-interest map without
         * palette locking. Entries are keyed by local position hashes, and
         * later position-based updates remain visible through the view.
         */
        Map<Integer, PoiType> getPoiEntries();

        /**
         * Stores a point-of-interest type in the backing concurrent position
         * map after coordinate validation. An existing type at that position is
         * replaced without changing block palettes or viewer updates.
         */
        void addPoi(int x, int y, int z, PoiType type);

        /**
         * Removes a point-of-interest entry from the backing concurrent
         * position map after coordinate validation. An absent entry has no
         * effect, and block palettes remain independent of this mapping.
         */
        void removePoi(int x, int y, int z);

        /**
         * Returns the point-of-interest type from the backing concurrent
         * position map, or {@code null} when absent. Coordinates are validated without
         * acquiring block or biome locks for the lookup.
         */
        PoiType getPoi(int x, int y, int z);

        /**
         * Returns the shared section selected by a validated absolute section
         * Y. Delegation does not acquire palette locks, so subsequent
         * operations on the section require explicit chunk locking.
         */
        ChunkSection getSection(int sectionY);

        /**
         * Returns an unmodifiable section list containing the backing chunk's
         * shared mutable sections. No palette locks are acquired or retained,
         * so callers must coordinate later section access.
         */
        List<ChunkSection> getSections();

        /**
         * Returns the backing chunk's cached safe wrapper over the same shared
         * data. This identity conversion acquires no locks and does not copy
         * sections, entities, or auxiliary maps.
         */
        Chunk toSafeChunk();
    }
}
