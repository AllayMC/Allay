package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound sent continuously while a player is breaking a block.
 *
 * @param blockState the block state being broken; the sound usually depends on the block type
 */
public record BlockBreakingSound(BlockState blockState) implements Sound {
}
