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
public interface ItemElement65 extends ItemStack {
    ItemType<ItemElement65> TYPE = ItemTypeBuilder
            .builder(ItemElement65.class)
            .vanillaItem(VanillaItemId.ELEMENT_65, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
