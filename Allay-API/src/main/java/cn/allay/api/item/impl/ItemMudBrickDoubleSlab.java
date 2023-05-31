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
public interface ItemMudBrickDoubleSlab extends ItemStack {
    ItemType<ItemMudBrickDoubleSlab> TYPE = ItemTypeBuilder
            .builder(ItemMudBrickDoubleSlab.class)
            .vanillaItem(VanillaItemId.MUD_BRICK_DOUBLE_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
