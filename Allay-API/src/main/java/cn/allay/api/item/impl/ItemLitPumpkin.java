package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitPumpkin extends ItemStack {
    ItemType<ItemLitPumpkin> TYPE = ItemTypeBuilder
            .builder(ItemLitPumpkin.class)
            .vanillaItem(VanillaItemId.LIT_PUMPKIN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
