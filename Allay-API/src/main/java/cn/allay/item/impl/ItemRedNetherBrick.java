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
public interface ItemRedNetherBrick extends ItemStack {
    ItemType<ItemRedNetherBrick> TYPE = ItemTypeBuilder
            .builder(ItemRedNetherBrick.class)
            .vanillaItem(VanillaItemId.RED_NETHER_BRICK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
