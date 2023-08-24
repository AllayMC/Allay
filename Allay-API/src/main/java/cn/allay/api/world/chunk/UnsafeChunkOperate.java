package cn.allay.api.world.chunk;

import java.util.concurrent.locks.StampedLock;

/**
 * Allay Project 8/24/2023
 *
 * @author Cool_Loong
 */
@FunctionalInterface
public interface UnsafeChunkOperate {
    void run(StampedLock blockLock, StampedLock heightAndBiomeLock, StampedLock lightLock, UnsafeChunk unsafeChunk);
}
