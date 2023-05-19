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
public interface ItemLitSmoker extends ItemStack {
    ItemType<ItemLitSmoker> TYPE = ItemTypeBuilder
            .builder(ItemLitSmoker.class)
            .vanillaItem(VanillaItemId.LIT_SMOKER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
