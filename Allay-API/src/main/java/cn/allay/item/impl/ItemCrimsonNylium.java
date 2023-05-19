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
public interface ItemCrimsonNylium extends ItemStack {
    ItemType<ItemCrimsonNylium> TYPE = ItemTypeBuilder
            .builder(ItemCrimsonNylium.class)
            .vanillaItem(VanillaItemId.CRIMSON_NYLIUM, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
