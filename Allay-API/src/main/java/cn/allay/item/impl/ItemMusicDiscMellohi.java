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
public interface ItemMusicDiscMellohi extends ItemStack {
    ItemType<ItemMusicDiscMellohi> TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscMellohi.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_MELLOHI, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
