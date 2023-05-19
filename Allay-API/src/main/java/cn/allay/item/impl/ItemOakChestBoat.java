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
public interface ItemOakChestBoat extends ItemStack {
    ItemType<ItemOakChestBoat> TYPE = ItemTypeBuilder
            .builder(ItemOakChestBoat.class)
            .vanillaItem(VanillaItemId.OAK_CHEST_BOAT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
