package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a pressure plate is deactivated.
 *
 * @param blockState the pressure plate block state; the sound may depend on the block type
 * @author daoge_cmd
 */
public record PressurePlateDeactivateSound(BlockState blockState) implements Sound {
}
