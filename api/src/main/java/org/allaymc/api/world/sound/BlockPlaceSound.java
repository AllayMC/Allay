package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code BlockPlaceSound} describes a block-placement sound using the placed
 * state as its material. The runtime sends that state hash with the placement
 * event; constructing this record does not place a block or schedule the sound
 * for viewers.
 *
 * @param blockState placed state supplying the sound material
 */
public record BlockPlaceSound(BlockState blockState) implements Sound {
}
