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
public interface ItemGoldenRail extends ItemStack {
    ItemType<ItemGoldenRail> TYPE = ItemTypeBuilder
            .builder(ItemGoldenRail.class)
            .vanillaItem(VanillaItemId.GOLDEN_RAIL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
