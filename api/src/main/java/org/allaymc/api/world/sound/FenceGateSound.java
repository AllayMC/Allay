package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a fence gate is opened or closed.
 *
 * @param blockState the fence gate block state; the sound typically depends on the block type
 * @param open       true if the fence gate is opening, false if closing
 */
public record FenceGateSound(BlockState blockState, boolean open) implements Sound {
}
