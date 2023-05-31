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
public interface ItemChestBoat extends ItemStack {
    ItemType<ItemChestBoat> TYPE = ItemTypeBuilder
            .builder(ItemChestBoat.class)
            .vanillaItem(VanillaItemId.CHEST_BOAT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
