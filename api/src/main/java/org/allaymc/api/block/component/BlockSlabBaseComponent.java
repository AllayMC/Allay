package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public interface BlockSlabBaseComponent extends BlockBaseComponent {
    /**
     * Get the double slab block type.
     *
     * @return the double slab block type.
     */
    BlockType<?> getDoubleSlabBlockType();
}
