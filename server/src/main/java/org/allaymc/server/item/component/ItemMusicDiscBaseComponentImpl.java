package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.item.component.ItemMusicDiscBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author IWareQ
 */
@Getter
public class ItemMusicDiscBaseComponentImpl extends ItemBaseComponentImpl implements ItemMusicDiscBaseComponent {
    private final SoundEvent sound;

    public ItemMusicDiscBaseComponentImpl(ItemStackInitInfo initInfo, SoundEvent sound) {
        super(initInfo);
        this.sound = sound;
    }
}
