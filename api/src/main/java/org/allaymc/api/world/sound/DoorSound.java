package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a door or trapdoor is opened or closed.
 *
 * @param blockState the door/trapdoor block state; the sound typically depends on the block type
 * @param open       whether the door is being opened (true) or closed (false)
 * @author daoge_cmd
 */
public record DoorSound(BlockState blockState, boolean open) implements Sound {
}
