package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a trapdoor is opened.
 *
 * @param blockState the trapdoor block state being opened; the sound typically depends on the block type
 */
public record TrapdoorOpenSound(BlockState blockState) implements Sound {
}
