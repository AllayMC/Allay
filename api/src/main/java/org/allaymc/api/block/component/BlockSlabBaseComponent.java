package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public interface BlockSlabBaseComponent extends BlockBaseComponent {
    /**
     * Gets the double slab block type.
     *
     * @return the corresponding {@link BlockType}
     */
    BlockType<?> getDoubleSlabBlockType();
}
