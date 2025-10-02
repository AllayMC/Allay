package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.interfaces.ItemMusicDiscStack;

/**
 * @author IWareQ
 */
public interface BlockEntityJukeboxBaseComponent extends BlockEntityBaseComponent {

    /**
     * Play the music disc inside the jukebox.
     */
    void play();

    /**
     * Stop the music disc inside the jukebox.
     */
    void stop();

    /**
     * Get the music disc inside the jukebox.
     *
     * @return the music disc item
     */
    ItemMusicDiscStack getMusicDiscItem();

    /**
     * Set the music disc inside the jukebox.
     *
     * @param item the music disc item
     */
    void setMusicDiscItem(ItemMusicDiscStack item);
}
