package org.allaymc.api.item.component;

import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author IWareQ
 */
public interface ItemMusicDiscComponent extends ItemComponent {
    SoundEvent getSound();
}
