package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public interface BlockDoubleSlabBaseComponent extends BlockBaseComponent {
    /**
     * Get the single slab block type.
     *
     * @return the single slab block type.
     */
    BlockType<?> getSingleSlabBlockType();
}
