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
public interface ItemElement54 extends ItemStack {
    ItemType<ItemElement54> TYPE = ItemTypeBuilder
            .builder(ItemElement54.class)
            .vanillaItem(VanillaItemId.ELEMENT_54, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
