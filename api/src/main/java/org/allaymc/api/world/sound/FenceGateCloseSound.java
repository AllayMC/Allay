package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a fence gate is closed.
 *
 * @param blockState the fence gate block state being closed; the sound typically depends on the block type
 */
public record FenceGateCloseSound(BlockState blockState) implements Sound {
}
