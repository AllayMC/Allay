package org.allaymc.api.item.component;

import org.allaymc.api.block.data.BlockId;

/**
 * Represents a sign base component.
 *
 * @author daoge_cmd
 */
public interface ItemSignBaseComponent extends ItemBaseComponent {
    /**
     * Get the wall sign block id.
     *
     * @return The wall sign block id.
     */
    BlockId getWallSignId();

    /**
     * Get the standing sign block id.
     *
     * @return The standing sign block id.
     */
    BlockId getStandingSignId();
}
