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
public interface ItemItemChain extends ItemStack {
    ItemType<ItemItemChain> TYPE = ItemTypeBuilder
            .builder(ItemItemChain.class)
            .vanillaItem(VanillaItemId.ITEM_CHAIN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
