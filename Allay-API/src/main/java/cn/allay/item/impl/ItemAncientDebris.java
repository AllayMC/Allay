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
public interface ItemAncientDebris extends ItemStack {
    ItemType<ItemAncientDebris> TYPE = ItemTypeBuilder
            .builder(ItemAncientDebris.class)
            .vanillaItem(VanillaItemId.ANCIENT_DEBRIS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
