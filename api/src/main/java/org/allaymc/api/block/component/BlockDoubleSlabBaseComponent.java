package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockDoubleSlabBaseComponent} exposes the single slab counterpart associated
 * with a double slab block. The relationship lets drop handling resolve the original
 * slab item type while the inherited base component supplies the ordinary block
 * lifecycle contract.
 *
 * @author daoge_cmd
 */
public interface BlockDoubleSlabBaseComponent extends BlockBaseComponent {
    /**
     * Returns the single slab block type corresponding to this double slab.
     */
    BlockType<?> getSingleSlabBlockType();
}
