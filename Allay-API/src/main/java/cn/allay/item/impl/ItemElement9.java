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
public interface ItemElement9 extends ItemStack {
    ItemType<ItemElement9> TYPE = ItemTypeBuilder
            .builder(ItemElement9.class)
            .vanillaItem(VanillaItemId.ELEMENT_9, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
