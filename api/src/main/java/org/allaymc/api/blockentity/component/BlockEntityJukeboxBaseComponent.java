package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.interfaces.ItemMusicDiscStack;

/**
 * {@code BlockEntityJukeboxBaseComponent} exposes a jukebox's retained music
 * disc and playback controls. Disc assignment and playback are separate
 * operations: the server refreshes comparator output when the disc changes and
 * emits sounds only through the playback methods.
 *
 * @author IWareQ
 */
public interface BlockEntityJukeboxBaseComponent extends BlockEntityBaseComponent {

    /**
     * Emits playback for the currently retained music disc at the jukebox. The
     * server requires a non-{@code null} disc before this operation and does
     * not change or consume the retained item.
     */
    void play();

    /**
     * Emits the jukebox stop sound without clearing the retained disc.
     */
    void stop();

    /**
     * Returns the retained music-disc stack, or {@code null} when empty. The
     * server exposes the same mutable item reference used for saving and
     * playback rather than returning a defensive copy.
     */
    ItemMusicDiscStack getMusicDiscItem();

    /**
     * Retains the supplied disc, or clears the selection for {@code null}. The
     * server refreshes comparator output without starting or stopping playback,
     * and the caller retains access to the same mutable stack.
     */
    void setMusicDiscItem(ItemMusicDiscStack item);
}
