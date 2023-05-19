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
public interface ItemYellowCandleCake extends ItemStack {
    ItemType<ItemYellowCandleCake> TYPE = ItemTypeBuilder
            .builder(ItemYellowCandleCake.class)
            .vanillaItem(VanillaItemId.YELLOW_CANDLE_CAKE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
