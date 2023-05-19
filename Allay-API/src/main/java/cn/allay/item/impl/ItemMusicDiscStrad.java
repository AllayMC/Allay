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
public interface ItemMusicDiscStrad extends ItemStack {
    ItemType<ItemMusicDiscStrad> TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscStrad.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_STRAD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
