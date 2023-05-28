package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedMushroomBlock extends ItemStack {
    ItemType<ItemRedMushroomBlock> TYPE = ItemTypeBuilder
            .builder(ItemRedMushroomBlock.class)
            .vanillaItem(VanillaItemId.RED_MUSHROOM_BLOCK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
