package org.allaymc.api.block.component;

import org.allaymc.api.block.dto.Block;

/**
 * {@code BlockLecternBaseComponent} defines page-turn handling for lectern block
 * interactions. A reading interface supplies the lectern and requested page
 * through {@code onPageTurn}; the block implementation applies its event and
 * redstone rules while retaining the inherited base block operations.
 *
 * @author daoge_cmd
 */
public interface BlockLecternBaseComponent extends BlockBaseComponent {

    /**
     * Processes a requested page change for the supplied lectern. The built-in
     * implementation ignores an empty lectern and an unchanged page, then permits
     * {@code LecternPageTurnEvent} handlers to cancel or replace the page before
     * updating the book and emitting a redstone pulse.
     */
    void onPageTurn(Block block, int newPage);
}
