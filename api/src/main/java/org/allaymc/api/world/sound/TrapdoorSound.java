package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a trapdoor is opened or closed.
 *
 * @param blockState the trapdoor block state; the sound typically depends on the block type
 * @param open       whether the trapdoor is being opened (true) or closed (false)
 * @author daoge_cmd
 */
public record TrapdoorSound(BlockState blockState, boolean open) implements Sound {
}
