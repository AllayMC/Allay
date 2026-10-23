package org.allaymc.api.item.component;

import org.allaymc.api.item.data.DiscType;

/**
 * {@code ItemMusicDiscBaseComponent} exposes the music-disc kind represented by an
 * item and its jukebox comparator signal. The default signal query delegates to
 * the disc kind's configured value, allowing multiple discs to share a signal
 * without deriving it from enum order.
 *
 * @author IWareQ
 */
public interface ItemMusicDiscBaseComponent extends ItemBaseComponent {
    /**
     * Returns the music-disc kind represented by this item stack.
     */
    DiscType getDiscType();

    /**
     * Returns this disc kind's configured jukebox comparator signal strength.
     */
    default int getComparatorSignal() {
        return getDiscType().getComparatorSignal();
    }
}
