package cn.allay.api.item.interfaces.musicdiscwait;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscWaitStack extends ItemStack {
    ItemType<ItemMusicDiscWaitStack> MUSIC_DISC_WAIT_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscWaitStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_WAIT)
            .build();
}
