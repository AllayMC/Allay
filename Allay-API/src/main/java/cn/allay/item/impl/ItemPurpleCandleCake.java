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
public interface ItemPurpleCandleCake extends ItemStack {
    ItemType<ItemPurpleCandleCake> TYPE = ItemTypeBuilder
            .builder(ItemPurpleCandleCake.class)
            .vanillaItem(VanillaItemId.PURPLE_CANDLE_CAKE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
