package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code PressurePlateSound} describes a pressure plate's activation or
 * release click. The runtime selects the event from {@code activated} and
 * supplies the state hash for its material, while the caller changes the plate
 * state independently of broadcasting the sound.
 *
 * @param blockState plate state supplying the sound material
 *
 * @param activated {@code true} for activation, {@code false} for release
 *
 * @author daoge_cmd
 */
public record PressurePlateSound(BlockState blockState, boolean activated) implements Sound {
}
