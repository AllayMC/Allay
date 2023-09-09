package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDisc13Stack extends ItemStack {
    ItemType<ItemMusicDisc13Stack> MUSIC_DISC_13_TYPE = ItemTypeBuilder
            .builder(ItemMusicDisc13Stack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_13)
            .build();
}
