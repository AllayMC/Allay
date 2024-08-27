package org.allaymc.api.item.component;

import org.allaymc.api.block.data.BlockId;

/**
 * Allay Project 2024/8/15
 *
 * @author daoge_cmd
 */
public interface ItemSignBaseComponent extends ItemBaseComponent {
    BlockId getWallSignId();

    BlockId getStandingSignId();
}
