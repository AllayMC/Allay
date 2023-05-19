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
public interface ItemTropicalFish extends ItemStack {
    ItemType<ItemTropicalFish> TYPE = ItemTypeBuilder
            .builder(ItemTropicalFish.class)
            .vanillaItem(VanillaItemId.TROPICAL_FISH, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
