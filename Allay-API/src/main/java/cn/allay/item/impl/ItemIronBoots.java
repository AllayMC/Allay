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
public interface ItemIronBoots extends ItemStack {
    ItemType<ItemIronBoots> TYPE = ItemTypeBuilder
            .builder(ItemIronBoots.class)
            .vanillaItem(VanillaItemId.IRON_BOOTS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
