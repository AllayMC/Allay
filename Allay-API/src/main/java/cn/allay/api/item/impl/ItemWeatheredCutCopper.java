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
public interface ItemWeatheredCutCopper extends ItemStack {
    ItemType<ItemWeatheredCutCopper> TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCutCopper.class)
            .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
