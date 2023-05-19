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
public interface ItemEndBricks extends ItemStack {
    ItemType<ItemEndBricks> TYPE = ItemTypeBuilder
            .builder(ItemEndBricks.class)
            .vanillaItem(VanillaItemId.END_BRICKS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
