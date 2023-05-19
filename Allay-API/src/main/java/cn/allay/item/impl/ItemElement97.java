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
public interface ItemElement97 extends ItemStack {
    ItemType<ItemElement97> TYPE = ItemTypeBuilder
            .builder(ItemElement97.class)
            .vanillaItem(VanillaItemId.ELEMENT_97, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
