package org.allaymc.api.block.property;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;

/**
 * Allay Project 2024/3/2
 *
 * @author daoge_cmd
 */
public class PropertyUtil {
    public static MinecraftCardinalDirection toMinecraftCardinalDirection(BlockFace face) {
        return switch (face) {
            case NORTH -> MinecraftCardinalDirection.NORTH;
            case SOUTH -> MinecraftCardinalDirection.SOUTH;
            case EAST -> MinecraftCardinalDirection.EAST;
            case WEST -> MinecraftCardinalDirection.WEST;
            default -> null;
        };
    }
}
