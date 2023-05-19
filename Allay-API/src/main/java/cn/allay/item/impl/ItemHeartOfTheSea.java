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
public interface ItemHeartOfTheSea extends ItemStack {
    ItemType<ItemHeartOfTheSea> TYPE = ItemTypeBuilder
            .builder(ItemHeartOfTheSea.class)
            .vanillaItem(VanillaItemId.HEART_OF_THE_SEA, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
