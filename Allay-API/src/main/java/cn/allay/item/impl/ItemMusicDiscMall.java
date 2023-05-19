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
public interface ItemMusicDiscMall extends ItemStack {
    ItemType<ItemMusicDiscMall> TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscMall.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_MALL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
