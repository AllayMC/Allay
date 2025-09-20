package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a trapdoor is closed.
 *
 * @param blockState the trapdoor block state being closed; the sound typically depends on the block type
 */
public record TrapdoorCloseSound(BlockState blockState) implements Sound {
}
