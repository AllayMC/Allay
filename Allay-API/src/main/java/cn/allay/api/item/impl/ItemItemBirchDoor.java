package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBirchDoor extends ItemStack {
    ItemType<ItemItemBirchDoor> TYPE = ItemTypeBuilder
            .builder(ItemItemBirchDoor.class)
            .vanillaItem(VanillaItemId.ITEM_BIRCH_DOOR, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
