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
public interface ItemItemAcaciaDoor extends ItemStack {
    ItemType<ItemItemAcaciaDoor> TYPE = ItemTypeBuilder
            .builder(ItemItemAcaciaDoor.class)
            .vanillaItem(VanillaItemId.ITEM_ACACIA_DOOR, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
