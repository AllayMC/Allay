package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.item.component.ItemMusicDiscBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.world.sound.Sound;

/**
 * @author IWareQ | daoge_cmd
 */
@Getter
public class ItemMusicDiscBaseComponentImpl extends ItemBaseComponentImpl implements ItemMusicDiscBaseComponent {
    private final Sound sound;

    public ItemMusicDiscBaseComponentImpl(ItemStackInitInfo initInfo, Sound sound) {
        super(initInfo);
        this.sound = sound;
    }
}
