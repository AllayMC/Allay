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
public interface ItemElement111 extends ItemStack {
    ItemType<ItemElement111> TYPE = ItemTypeBuilder
            .builder(ItemElement111.class)
            .vanillaItem(VanillaItemId.ELEMENT_111, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
