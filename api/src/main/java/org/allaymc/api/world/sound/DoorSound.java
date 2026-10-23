package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code DoorSound} describes opening or closing a door using its block
 * material. The runtime selects the event from {@code open} and sends the
 * state hash as its material payload; trapdoors also have a dedicated {@link
 * TrapdoorSound} descriptor.
 *
 * @param blockState door state supplying the sound material
 *
 * @param open {@code true} for opening, {@code false} for closing
 *
 * @author daoge_cmd
 */
public record DoorSound(BlockState blockState, boolean open) implements Sound {
}
