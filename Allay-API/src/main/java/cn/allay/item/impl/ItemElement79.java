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
public interface ItemElement79 extends ItemStack {
    ItemType<ItemElement79> TYPE = ItemTypeBuilder
            .builder(ItemElement79.class)
            .vanillaItem(VanillaItemId.ELEMENT_79, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
