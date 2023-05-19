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
public interface ItemNetherbrick0 extends ItemStack {
    ItemType<ItemNetherbrick0> TYPE = ItemTypeBuilder
            .builder(ItemNetherbrick0.class)
            .vanillaItem(VanillaItemId.NETHERBRICK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
