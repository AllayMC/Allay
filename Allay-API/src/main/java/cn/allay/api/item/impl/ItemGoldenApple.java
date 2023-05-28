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
public interface ItemGoldenApple extends ItemStack {
    ItemType<ItemGoldenApple> TYPE = ItemTypeBuilder
            .builder(ItemGoldenApple.class)
            .vanillaItem(VanillaItemId.GOLDEN_APPLE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
