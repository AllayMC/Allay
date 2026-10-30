package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code TrapdoorSound} describes opening or closing a trapdoor using its
 * block material. The runtime selects the trapdoor event from {@code open} and
 * sends the supplied state hash; callers perform the trapdoor state change
 * separately from broadcasting the sound.
 *
 * @param blockState trapdoor state supplying the sound material
 *
 * @param open {@code true} for opening, {@code false} for closing
 *
 * @author daoge_cmd
 */
public record TrapdoorSound(BlockState blockState, boolean open) implements Sound {
}
