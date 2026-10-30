package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemMusicDiscBaseComponent;
import org.allaymc.api.item.data.DiscType;

/**
 * {@code ItemMusicDiscBaseComponentImpl} associates a music-disc stack with its
 * configured disc kind. The generated getter supplies that kind to the shared
 * disc interface, whose comparator query uses the kind's explicit signal value
 * rather than item metadata.
 *
 * @author IWareQ | daoge_cmd
 */
@Getter
public class ItemMusicDiscBaseComponentImpl extends ItemBaseComponentImpl implements ItemMusicDiscBaseComponent {
    /**
     * Configured music-disc kind value.
     */
    private final DiscType discType;

    /**
     * Creates a music-disc component using the supplied stack initialization and kind.
     */
    public ItemMusicDiscBaseComponentImpl(ItemStackInitInfo initInfo, DiscType discType) {
        super(initInfo);
        this.discType = discType;
    }
}
