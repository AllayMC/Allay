package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound sent when a block is placed.
 *
 * @param blockState the block state that was placed; the actual sound to play typically depends on
 *                   the type of the block
 */
public record BlockPlaceSound(BlockState blockState) implements Sound {
}
