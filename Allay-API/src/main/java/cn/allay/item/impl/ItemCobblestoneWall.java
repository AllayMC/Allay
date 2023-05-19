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
public interface ItemCobblestoneWall extends ItemStack {
    ItemType<ItemCobblestoneWall> TYPE = ItemTypeBuilder
            .builder(ItemCobblestoneWall.class)
            .vanillaItem(VanillaItemId.COBBLESTONE_WALL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
