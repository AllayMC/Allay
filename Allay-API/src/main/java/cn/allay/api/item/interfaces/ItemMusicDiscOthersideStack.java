package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscOthersideStack extends ItemStack {
    ItemType<ItemMusicDiscOthersideStack> MUSIC_DISC_OTHERSIDE_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscOthersideStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_OTHERSIDE)
            .build();
}
