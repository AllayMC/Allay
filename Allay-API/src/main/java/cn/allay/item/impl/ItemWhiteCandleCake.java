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
public interface ItemWhiteCandleCake extends ItemStack {
    ItemType<ItemWhiteCandleCake> TYPE = ItemTypeBuilder
            .builder(ItemWhiteCandleCake.class)
            .vanillaItem(VanillaItemId.WHITE_CANDLE_CAKE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
