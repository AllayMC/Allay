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
public interface ItemMudBrickSlab extends ItemStack {
    ItemType<ItemMudBrickSlab> TYPE = ItemTypeBuilder
            .builder(ItemMudBrickSlab.class)
            .vanillaItem(VanillaItemId.MUD_BRICK_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
