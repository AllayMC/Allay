package org.allaymc.api.item.component;

import org.allaymc.api.item.data.DiscType;

/**
 * @author IWareQ
 */
public interface ItemMusicDiscBaseComponent extends ItemBaseComponent {
    /**
     * Get the type of the music disc.
     *
     * @return the type of the music disc
     */
    DiscType getDiscType();
}
