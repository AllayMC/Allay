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
public interface ItemDeepslateTiles extends ItemStack {
    ItemType<ItemDeepslateTiles> TYPE = ItemTypeBuilder
            .builder(ItemDeepslateTiles.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_TILES, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
