package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockType;

/**
 * @author IWareQ
 */
public interface BlockInfestedBlockBaseComponent extends BlockBaseComponent {
    /**
     * Retrieves the block type that is imitated by the current block.
     *
     * @return The {@link BlockType} of the imitated block
     */
    BlockType<?> getImitatedBlock();
}
