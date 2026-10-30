package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockSlabBaseComponent} exposes the double slab counterpart associated with
 * a single slab block. Placement can use this relationship to combine matching slabs
 * into a full block, while the inherited base component supplies general lifecycle
 * operations.
 *
 * @author daoge_cmd
 */
public interface BlockSlabBaseComponent extends BlockBaseComponent {
    /**
     * Returns the double slab block type corresponding to this slab.
     */
    BlockType<?> getDoubleSlabBlockType();
}
