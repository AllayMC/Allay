package cn.allay.api.world.generator;

import cn.allay.api.world.World;
import cn.allay.api.world.chunk.ChunkAccessible;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface LimitedWorldRegion extends ChunkAccessible {
    World getWorld();
}
