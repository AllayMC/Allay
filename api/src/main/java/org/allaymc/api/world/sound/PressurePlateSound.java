package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a pressure plate is activated or deactivated.
 *
 * @param blockState the pressure plate block state; the sound may depend on the block type
 * @param activated  whether the pressure plate is being activated (true) or deactivated (false)
 * @author daoge_cmd
 */
public record PressurePlateSound(BlockState blockState, boolean activated) implements Sound {
}
