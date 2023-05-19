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
public interface ItemWaxedOxidizedCutCopperStairs extends ItemStack {
    ItemType<ItemWaxedOxidizedCutCopperStairs> TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCutCopperStairs.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
