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
public interface ItemCarrot extends ItemStack {
    ItemType<ItemCarrot> TYPE = ItemTypeBuilder
            .builder(ItemCarrot.class)
            .vanillaItem(VanillaItemId.CARROT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
