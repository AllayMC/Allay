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
public interface ItemPurpleWool extends ItemStack {
    ItemType<ItemPurpleWool> TYPE = ItemTypeBuilder
            .builder(ItemPurpleWool.class)
            .vanillaItem(VanillaItemId.PURPLE_WOOL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
