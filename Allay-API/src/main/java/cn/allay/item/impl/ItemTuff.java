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
public interface ItemTuff extends ItemStack {
    ItemType<ItemTuff> TYPE = ItemTypeBuilder
            .builder(ItemTuff.class)
            .vanillaItem(VanillaItemId.TUFF, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
