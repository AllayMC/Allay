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
public interface ItemYellowDye extends ItemStack {
    ItemType<ItemYellowDye> TYPE = ItemTypeBuilder
            .builder(ItemYellowDye.class)
            .vanillaItem(VanillaItemId.YELLOW_DYE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
