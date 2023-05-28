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
public interface ItemElement92 extends ItemStack {
    ItemType<ItemElement92> TYPE = ItemTypeBuilder
            .builder(ItemElement92.class)
            .vanillaItem(VanillaItemId.ELEMENT_92, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
