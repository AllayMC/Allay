package org.allaymc.api.block.component;

import org.allaymc.api.block.dto.Block;

/**
 * Base component for lectern block behavior.
 *
 * @author daoge_cmd
 */
public interface BlockLecternBaseComponent extends BlockBaseComponent {

    /**
     * Called when a player turns a page in the lectern.
     * This is typically called from a network packet handler.
     *
     * @param block   the lectern block
     * @param newPage the new page number
     */
    void onPageTurn(Block block, int newPage);
}
