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
public interface ItemMuddyMangroveRoots extends ItemStack {
    ItemType<ItemMuddyMangroveRoots> TYPE = ItemTypeBuilder
            .builder(ItemMuddyMangroveRoots.class)
            .vanillaItem(VanillaItemId.MUDDY_MANGROVE_ROOTS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
