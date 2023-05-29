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
public interface ItemItemDarkOakDoor extends ItemStack {
    ItemType<ItemItemDarkOakDoor> TYPE = ItemTypeBuilder
            .builder(ItemItemDarkOakDoor.class)
            .vanillaItem(VanillaItemId.ITEM_DARK_OAK_DOOR, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
