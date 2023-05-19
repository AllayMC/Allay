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
public interface ItemBeeNest extends ItemStack {
    ItemType<ItemBeeNest> TYPE = ItemTypeBuilder
            .builder(ItemBeeNest.class)
            .vanillaItem(VanillaItemId.BEE_NEST, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
