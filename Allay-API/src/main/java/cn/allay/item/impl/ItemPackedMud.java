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
public interface ItemPackedMud extends ItemStack {
    ItemType<ItemPackedMud> TYPE = ItemTypeBuilder
            .builder(ItemPackedMud.class)
            .vanillaItem(VanillaItemId.PACKED_MUD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
