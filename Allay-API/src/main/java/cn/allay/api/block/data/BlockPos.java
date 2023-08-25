package cn.allay.api.block.data;

import cn.allay.api.world.World;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
public record BlockPos(
        World world,
        int x,
        int y,
        int z,
        boolean layer
) {
}
