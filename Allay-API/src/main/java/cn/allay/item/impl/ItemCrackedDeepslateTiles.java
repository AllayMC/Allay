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
public interface ItemCrackedDeepslateTiles extends ItemStack {
    ItemType<ItemCrackedDeepslateTiles> TYPE = ItemTypeBuilder
            .builder(ItemCrackedDeepslateTiles.class)
            .vanillaItem(VanillaItemId.CRACKED_DEEPSLATE_TILES, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
