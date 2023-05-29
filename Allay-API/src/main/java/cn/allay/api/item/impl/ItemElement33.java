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
public interface ItemElement33 extends ItemStack {
    ItemType<ItemElement33> TYPE = ItemTypeBuilder
            .builder(ItemElement33.class)
            .vanillaItem(VanillaItemId.ELEMENT_33, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
