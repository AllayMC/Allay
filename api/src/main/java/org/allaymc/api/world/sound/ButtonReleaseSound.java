package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code ButtonReleaseSound} describes the release click of a button block.
 * The runtime sends the supplied state hash with the button-off event,
 * allowing the client to choose the material sound independently of the
 * operation that changes button state.
 *
 * @param blockState button state supplying the sound material
 *
 * @author daoge_cmd
 */
public record ButtonReleaseSound(BlockState blockState) implements Sound {
}
