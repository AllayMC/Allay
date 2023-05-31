package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryLeaves extends ItemStack {
    ItemType<ItemCherryLeaves> TYPE = ItemTypeBuilder
            .builder(ItemCherryLeaves.class)
            .vanillaItem(VanillaItemId.CHERRY_LEAVES, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
