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
public interface ItemInfoUpdate2 extends ItemStack {
    ItemType<ItemInfoUpdate2> TYPE = ItemTypeBuilder
            .builder(ItemInfoUpdate2.class)
            .vanillaItem(VanillaItemId.INFO_UPDATE2, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
