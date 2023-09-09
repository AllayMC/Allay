package cn.allay.api.world.generator;

import cn.allay.api.world.World;
import cn.allay.api.world.chunk.UnsafeChunk;

/**
 * Allay Project 8/23/2023
 *
 * @author Cool_Loong
 */
public record ChunkGenerateContext(UnsafeChunk chunk, World world) {
}
