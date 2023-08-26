package cn.allay.api.world.generator;

import cn.allay.api.world.World;
import cn.allay.api.world.chunk.UnsafeChunk;
import lombok.Data;

/**
 * Allay Project 8/23/2023
 *
 * @author Cool_Loong
 */
@Data
public class ChunkGenerateContext {
    private final UnsafeChunk chunk;
    private final World world;
}
