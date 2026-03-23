package org.allaymc.api.item.component;

import org.allaymc.api.block.data.PotSherds;

/**
 * Shared component for decorated pot items that store their sherd layout.
 *
 * @author IWareQ
 */
public interface ItemDecoratedPotBaseComponent extends ItemBaseComponent {
    /**
     * Get the sherds on this decorated pot item.
     *
     * @return the pot sherds
     */
    PotSherds getSherds();

    /**
     * Set the sherds on this decorated pot item.
     *
     * @param sherds the pot sherds
     */
    void setSherds(PotSherds sherds);
}
