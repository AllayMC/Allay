package org.allaymc.api.world.chunk;

import javax.annotation.concurrent.ThreadSafe;
import java.util.function.Consumer;

/**
 * Chunk represents a 16x16 area in a world.
 * <p>
 * All methods in this class are thread-safe. However, frequent calls to methods in this class
 * result in huge lock overhead. If you are sure that the instance won't be accessed by multiple threads,
 * you can operate on unsafe chunk directly. To get the unsafe chunk, use {@link #toUnsafeChunk()}.
 * <p>
 * Compared to {@link UnsafeChunk}, this class contains more methods, which are also guaranteed to be thread-safe.
 *
 * @author Cool_Loong | daoge_cmd
 */
@ThreadSafe
public interface Chunk extends UnsafeChunk {

    /**
     * Apply the chunk with the specified operation.
     *
     * @param operation the operation to be applied in the chunk.
     * @param block     the operation type of block.
     * @param biome     the operation type of biome.
     */
    void applyOperation(Consumer<UnsafeChunk> operation, OperationType block, OperationType biome);

    /**
     * Apply the section in the chunk with the specified operation.
     *
     * @param sectionY  the section y of the section.
     * @param operation the operation to be applied in the chunk section.
     * @param block     the operation type of block.
     * @param biome     the operation type of biome.
     */
    void applyOperationInSection(int sectionY, Consumer<ChunkSection> operation, OperationType block, OperationType biome);

    /**
     * Convert this chunk to a {@link UnsafeChunk} which is unsafe in multithreaded environment.
     *
     * @return the unsafe chunk.
     */
    UnsafeChunk toUnsafeChunk();
}
