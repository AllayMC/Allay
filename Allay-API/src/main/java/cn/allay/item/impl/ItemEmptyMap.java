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
public interface ItemEmptyMap extends ItemStack {
    ItemType<ItemEmptyMap> TYPE = ItemTypeBuilder
            .builder(ItemEmptyMap.class)
            .vanillaItem(VanillaItemId.EMPTY_MAP, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
