package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public interface BlockDoubleSlabBaseComponent extends BlockBaseComponent {
    /**
     * Gets the single slab block type.
     *
     * @return the corresponding {@link BlockType}
     */
    BlockType<?> getSingleSlabBlockType();
}
