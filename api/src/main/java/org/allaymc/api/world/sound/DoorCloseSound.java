package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a door is closed.
 *
 * @param blockState the door block state being closed; the sound typically depends on the block type
 */
public record DoorCloseSound(BlockState blockState) implements Sound {
}
