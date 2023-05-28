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
public interface ItemNetherbrick0 extends ItemStack {
    ItemType<ItemNetherbrick0> TYPE = ItemTypeBuilder
            .builder(ItemNetherbrick0.class)
            .vanillaItem(VanillaItemId.NETHERBRICK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
