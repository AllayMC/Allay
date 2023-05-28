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
public interface ItemElement78 extends ItemStack {
    ItemType<ItemElement78> TYPE = ItemTypeBuilder
            .builder(ItemElement78.class)
            .vanillaItem(VanillaItemId.ELEMENT_78, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
