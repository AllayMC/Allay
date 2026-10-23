package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code FenceGateSound} describes opening or closing a fence gate using its
 * block material. The runtime selects the event from {@code open} and sends
 * the supplied state hash, while callers perform the gate state change
 * separately from broadcasting its sound.
 *
 * @param blockState gate state supplying the sound material
 *
 * @param open {@code true} for opening, {@code false} for closing
 */
public record FenceGateSound(BlockState blockState, boolean open) implements Sound {
}
