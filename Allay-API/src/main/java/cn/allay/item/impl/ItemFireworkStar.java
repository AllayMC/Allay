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
public interface ItemFireworkStar extends ItemStack {
    ItemType<ItemFireworkStar> TYPE = ItemTypeBuilder
            .builder(ItemFireworkStar.class)
            .vanillaItem(VanillaItemId.FIREWORK_STAR, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
