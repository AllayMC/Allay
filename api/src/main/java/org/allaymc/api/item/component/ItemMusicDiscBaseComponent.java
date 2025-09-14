package org.allaymc.api.item.component;

import org.allaymc.api.world.sound.Sound;

/**
 * @author IWareQ
 */
public interface ItemMusicDiscBaseComponent extends ItemBaseComponent {
    /**
     * Get the sound of the music disc.
     *
     * @return the sound of the music disc
     */
    Sound getSound();
}
