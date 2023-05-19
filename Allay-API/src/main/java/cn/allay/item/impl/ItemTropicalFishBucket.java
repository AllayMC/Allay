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
public interface ItemTropicalFishBucket extends ItemStack {
    ItemType<ItemTropicalFishBucket> TYPE = ItemTypeBuilder
            .builder(ItemTropicalFishBucket.class)
            .vanillaItem(VanillaItemId.TROPICAL_FISH_BUCKET, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
