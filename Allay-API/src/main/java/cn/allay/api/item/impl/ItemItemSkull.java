package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSkull extends ItemStack {
    ItemType<ItemItemSkull> TYPE = ItemTypeBuilder
            .builder(ItemItemSkull.class)
            .vanillaItem(VanillaItemId.ITEM_SKULL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
