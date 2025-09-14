package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * Sound played when a player uses an item on a block (for example, using a shovel to make a path).
 *
 * <p>Note: in many cases the provided {@code blockState} is the *resulting* block state (the new block),
 * not the block that was present before the action.</p>
 *
 * @param blockState the resulting block state after the item use; the played sound often depends on this value
 */
public record ItemUseOnBlockSound(BlockState blockState) implements Sound {
}
