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
public interface ItemStrippedCrimsonStem extends ItemStack {
    ItemType<ItemStrippedCrimsonStem> TYPE = ItemTypeBuilder
            .builder(ItemStrippedCrimsonStem.class)
            .vanillaItem(VanillaItemId.STRIPPED_CRIMSON_STEM, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
