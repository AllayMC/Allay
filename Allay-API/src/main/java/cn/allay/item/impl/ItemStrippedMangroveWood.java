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
public interface ItemStrippedMangroveWood extends ItemStack {
    ItemType<ItemStrippedMangroveWood> TYPE = ItemTypeBuilder
            .builder(ItemStrippedMangroveWood.class)
            .vanillaItem(VanillaItemId.STRIPPED_MANGROVE_WOOD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
