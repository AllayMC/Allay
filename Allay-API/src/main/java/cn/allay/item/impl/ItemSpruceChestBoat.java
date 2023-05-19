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
public interface ItemSpruceChestBoat extends ItemStack {
    ItemType<ItemSpruceChestBoat> TYPE = ItemTypeBuilder
            .builder(ItemSpruceChestBoat.class)
            .vanillaItem(VanillaItemId.SPRUCE_CHEST_BOAT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
