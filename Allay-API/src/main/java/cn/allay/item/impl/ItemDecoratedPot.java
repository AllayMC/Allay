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
public interface ItemDecoratedPot extends ItemStack {
    ItemType<ItemDecoratedPot> TYPE = ItemTypeBuilder
            .builder(ItemDecoratedPot.class)
            .vanillaItem(VanillaItemId.DECORATED_POT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
