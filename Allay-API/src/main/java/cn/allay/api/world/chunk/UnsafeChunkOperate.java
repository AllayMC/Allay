package cn.allay.api.world.chunk;

/**
 * Allay Project 8/24/2023
 *
 * @author Cool_Loong
 */
@FunctionalInterface
public interface UnsafeChunkOperate {
    void run(UnsafeChunk unsafeChunk);
}
