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
public interface ItemElement27 extends ItemStack {
    ItemType<ItemElement27> TYPE = ItemTypeBuilder
            .builder(ItemElement27.class)
            .vanillaItem(VanillaItemId.ELEMENT_27, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
