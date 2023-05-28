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
public interface ItemItemReeds extends ItemStack {
    ItemType<ItemItemReeds> TYPE = ItemTypeBuilder
            .builder(ItemItemReeds.class)
            .vanillaItem(VanillaItemId.ITEM_REEDS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
