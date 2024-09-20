package org.allaymc.api.item.component;

import org.allaymc.api.block.data.BlockId;

/**
 * @author daoge_cmd
 */
public interface ItemSignBaseComponent extends ItemBaseComponent {
    BlockId getWallSignId();

    BlockId getStandingSignId();
}
