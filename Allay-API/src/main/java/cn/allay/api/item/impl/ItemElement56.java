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
public interface ItemElement56 extends ItemStack {
    ItemType<ItemElement56> TYPE = ItemTypeBuilder
            .builder(ItemElement56.class)
            .vanillaItem(VanillaItemId.ELEMENT_56, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
