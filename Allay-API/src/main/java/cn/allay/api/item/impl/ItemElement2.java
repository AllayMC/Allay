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
public interface ItemElement2 extends ItemStack {
    ItemType<ItemElement2> TYPE = ItemTypeBuilder
            .builder(ItemElement2.class)
            .vanillaItem(VanillaItemId.ELEMENT_2, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
