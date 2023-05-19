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
public interface ItemJungleBoat extends ItemStack {
    ItemType<ItemJungleBoat> TYPE = ItemTypeBuilder
            .builder(ItemJungleBoat.class)
            .vanillaItem(VanillaItemId.JUNGLE_BOAT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
