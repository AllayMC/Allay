package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a fence gate is opened.
 *
 * @param blockState the fence gate block state being opened; the sound typically depends on the block type
 */
public record FenceGateOpenSound(BlockState blockState) implements Sound {
}
