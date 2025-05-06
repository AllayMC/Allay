package org.allaymc.api.item.component;

import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author IWareQ
 */
public interface ItemMusicDiscBaseComponent extends ItemBaseComponent {
    /**
     * Get the sound of the music disc.
     *
     * @return the sound of the music disc.
     */
    SoundEvent getSound();
}
