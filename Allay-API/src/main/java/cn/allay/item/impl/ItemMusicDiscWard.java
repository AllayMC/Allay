package cn.allay.item.impl;

import cn.allay.item.ItemStack;
import cn.allay.item.data.VanillaItemId;
import cn.allay.item.type.ItemType;
import cn.allay.item.type.ItemTypeBuilder;
import cn.allay.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscWard extends ItemStack {
    ItemType<ItemMusicDiscWard> TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscWard.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_WARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
