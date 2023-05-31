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
public interface ItemElement31 extends ItemStack {
    ItemType<ItemElement31> TYPE = ItemTypeBuilder
            .builder(ItemElement31.class)
            .vanillaItem(VanillaItemId.ELEMENT_31, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
