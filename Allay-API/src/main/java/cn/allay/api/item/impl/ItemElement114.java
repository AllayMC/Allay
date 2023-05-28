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
public interface ItemElement114 extends ItemStack {
    ItemType<ItemElement114> TYPE = ItemTypeBuilder
            .builder(ItemElement114.class)
            .vanillaItem(VanillaItemId.ELEMENT_114, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
