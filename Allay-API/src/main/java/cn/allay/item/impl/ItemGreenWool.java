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
public interface ItemGreenWool extends ItemStack {
    ItemType<ItemGreenWool> TYPE = ItemTypeBuilder
            .builder(ItemGreenWool.class)
            .vanillaItem(VanillaItemId.GREEN_WOOL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
