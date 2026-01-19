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

    /**
     * Get the comparator signal output for this music disc when placed in a jukebox.
     *
     * @return the comparator signal (1-15)
     */
    default int getComparatorSignal() {
        return getDiscType().getComparatorSignal();
    }
}
