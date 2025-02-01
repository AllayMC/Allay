package org.allaymc.api.item.component;

import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author IWareQ
 */
public interface ItemMusicDiscComponent extends ItemComponent {
    /**
     * Get the sound of the music disc.
     *
     * @return the sound of the music disc.
     */
    SoundEvent getSound();
}
