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
public interface ItemElement24 extends ItemStack {
    ItemType<ItemElement24> TYPE = ItemTypeBuilder
            .builder(ItemElement24.class)
            .vanillaItem(VanillaItemId.ELEMENT_24, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
