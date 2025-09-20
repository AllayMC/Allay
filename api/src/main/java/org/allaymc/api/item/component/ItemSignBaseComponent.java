package org.allaymc.api.item.component;

import org.allaymc.api.block.type.BlockType;

/**
 * Represents a sign base component.
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
