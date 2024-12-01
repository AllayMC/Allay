package org.allaymc.server.item.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.component.ItemMusicDiscComponent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class ItemMusicDiscComponentImpl implements ItemMusicDiscComponent {
    private final SoundEvent sound;
}
