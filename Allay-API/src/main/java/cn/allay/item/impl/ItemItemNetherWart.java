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
public interface ItemItemNetherWart extends ItemStack {
    ItemType<ItemItemNetherWart> TYPE = ItemTypeBuilder
            .builder(ItemItemNetherWart.class)
            .vanillaItem(VanillaItemId.ITEM_NETHER_WART, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
