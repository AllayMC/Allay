package org.allaymc.api.world.chunk;

/**
 * UnsafeChunkOperate is used in {@link Chunk#batchProcess(UnsafeChunkOperate)} method.
 *
 * @author Cool_Loong
 */
@FunctionalInterface
public interface UnsafeChunkOperate {
    void run(UnsafeChunk unsafeChunk);
}
