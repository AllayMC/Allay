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
public interface ItemWaxedOxidizedCutCopper extends ItemStack {
    ItemType<ItemWaxedOxidizedCutCopper> TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCutCopper.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
