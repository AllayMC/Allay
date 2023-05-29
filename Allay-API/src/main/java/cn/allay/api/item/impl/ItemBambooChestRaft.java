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
public interface ItemBambooChestRaft extends ItemStack {
    ItemType<ItemBambooChestRaft> TYPE = ItemTypeBuilder
            .builder(ItemBambooChestRaft.class)
            .vanillaItem(VanillaItemId.BAMBOO_CHEST_RAFT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
