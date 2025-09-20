package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a door is opened.
 *
 * @param blockState the door block state being opened; the sound typically depends on the block type
 */
public record DoorOpenSound(BlockState blockState) implements Sound {
}
