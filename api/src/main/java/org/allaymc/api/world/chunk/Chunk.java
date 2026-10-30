package org.allaymc.api.world.chunk;

import org.allaymc.api.annotation.ThreadSafe;

import java.util.function.Consumer;

/**
 * {@code Chunk} provides
 * thread-safe operations on one sixteen-by-sixteen chunk column and
 * controlled access to its mutable sections. The thread-safety contract covers
 * method calls, while returned objects such as {@link #getSection(int)} retain
 * their own synchronization requirements. Callbacks declare block and biome
 * access separately; direct use of {@link #toUnsafeChunk()} requires the caller
 * to coordinate all conflicting access.
 *
 * @author Cool_Loong | daoge_cmd
 */
@ThreadSafe
public interface Chunk extends UnsafeChunk {

    /**
     * Runs a callback against this chunk with the requested block and biome
     * access modes. The callback must declare every accessed data category,
     * avoid upgrading held read locks to writes, and stop using the borrowed
     * unsafe view when protected access ends.
     */
    void applyOperation(Consumer<UnsafeChunk> operation, OperationType block, OperationType biome);

    /**
     * Runs a callback against one absolute section Y with the requested block
     * and biome access modes. Invalid section coordinates throw {@link
     * IllegalArgumentException}; the callback must restrict access to the
     * protected section and avoid upgrading read locks to writes.
     */
    void applyOperationInSection(int sectionY, Consumer<ChunkSection> operation, OperationType block, OperationType biome);

    /**
     * Returns the unsafe view sharing this chunk's underlying data rather than
     * a copy. Callers must establish compatible synchronization before using it
     * and must not assume that retaining the view retains any locks.
     */
    UnsafeChunk toUnsafeChunk();
}
