package cn.allay.api.item.interfaces.musicdisccat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscCatStack extends ItemStack {
    ItemType<ItemMusicDiscCatStack> MUSIC_DISC_CAT_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscCatStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_CAT)
            .build();
}
