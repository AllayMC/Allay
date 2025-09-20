package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a button is released.
 *
 * @param blockState the button block state released; the sound typically depends on the block state
 * @author daoge_cmd
 */
public record ButtonReleaseSound(BlockState blockState) implements Sound {
}
