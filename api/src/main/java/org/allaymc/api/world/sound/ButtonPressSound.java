package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a button is pressed.
 *
 * @param blockState the button block state being pressed; the sound typically depends on the block state
 * @author daoge_cmd
 */
public record ButtonPressSound(BlockState blockState) implements Sound {
}
