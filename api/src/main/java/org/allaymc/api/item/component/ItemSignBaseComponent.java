package org.allaymc.api.item.component;

import org.allaymc.api.block.type.BlockType;

/**
 * Shared component for sign items that resolve their standing and wall sign block variants.
 *
 * @author daoge_cmd
 */
public interface ItemSignBaseComponent extends ItemBaseComponent {
    /**
     * Get the wall sign block type.
     *
     * @return The wall sign block type
     */
    BlockType<?> getWallSignType();

    /**
     * Get the standing sign block type.
     *
     * @return The standing sign block type
     */
    BlockType<?> getStandingSignType();
}
