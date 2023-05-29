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
public interface ItemLightBlueCandleCake extends ItemStack {
    ItemType<ItemLightBlueCandleCake> TYPE = ItemTypeBuilder
            .builder(ItemLightBlueCandleCake.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CANDLE_CAKE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
