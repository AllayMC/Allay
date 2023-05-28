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
public interface ItemElement32 extends ItemStack {
    ItemType<ItemElement32> TYPE = ItemTypeBuilder
            .builder(ItemElement32.class)
            .vanillaItem(VanillaItemId.ELEMENT_32, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
