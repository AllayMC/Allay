package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;

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
     * @return the music disc item.
     */
    ItemStack getMusicDiscItem();

    /**
     * Set the music disc inside the jukebox.
     *
     * @param item the music disc item.
     */
    void setMusicDiscItem(ItemStack item);
}
