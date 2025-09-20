package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemMusicDiscBaseComponent;
import org.allaymc.api.item.data.DiscType;

/**
 * @author IWareQ | daoge_cmd
 */
@Getter
public class ItemMusicDiscBaseComponentImpl extends ItemBaseComponentImpl implements ItemMusicDiscBaseComponent {
    private final DiscType discType;

    public ItemMusicDiscBaseComponentImpl(ItemStackInitInfo initInfo, DiscType discType) {
        super(initInfo);
        this.discType = discType;
    }
}
