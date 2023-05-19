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
public interface ItemLeatherChestplate extends ItemStack {
    ItemType<ItemLeatherChestplate> TYPE = ItemTypeBuilder
            .builder(ItemLeatherChestplate.class)
            .vanillaItem(VanillaItemId.LEATHER_CHESTPLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
