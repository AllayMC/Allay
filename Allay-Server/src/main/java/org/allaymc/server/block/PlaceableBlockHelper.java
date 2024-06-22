package org.allaymc.server.block;

import org.allaymc.api.block.type.BlockType;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * Allay Project 2024/6/22
 *
 * @author daoge_cmd
 */
public interface PlaceableBlockHelper {
    static boolean isPlantPlaceableBlock(BlockType<?> block) {
        return block == GRASS_BLOCK_TYPE ||
               block == MYCELIUM_TYPE ||
               block == PODZOL_TYPE ||
               block == DIRT_TYPE ||
               block == DIRT_WITH_ROOTS_TYPE ||
               block == FARMLAND_TYPE ||
               block == MUD_TYPE ||
               block == MUDDY_MANGROVE_ROOTS_TYPE ||
               block == MOSS_BLOCK_TYPE;
    }
}
