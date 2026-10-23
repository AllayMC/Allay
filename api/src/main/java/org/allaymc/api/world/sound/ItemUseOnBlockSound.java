package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code ItemUseOnBlockSound} describes the material sound for an item
 * interaction with a block. Callers such as terrain-changing tools pass the
 * resulting state; the runtime sends the supplied state hash unchanged and
 * does not recover the block's previous state.
 *
 * @param blockState state supplying the interaction sound material
 */
public record ItemUseOnBlockSound(BlockState blockState) implements Sound {
}
