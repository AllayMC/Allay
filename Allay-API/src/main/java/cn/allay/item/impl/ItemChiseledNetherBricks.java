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
public interface ItemChiseledNetherBricks extends ItemStack {
    ItemType<ItemChiseledNetherBricks> TYPE = ItemTypeBuilder
            .builder(ItemChiseledNetherBricks.class)
            .vanillaItem(VanillaItemId.CHISELED_NETHER_BRICKS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
