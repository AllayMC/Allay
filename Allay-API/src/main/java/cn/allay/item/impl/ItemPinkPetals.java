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
public interface ItemPinkPetals extends ItemStack {
    ItemType<ItemPinkPetals> TYPE = ItemTypeBuilder
            .builder(ItemPinkPetals.class)
            .vanillaItem(VanillaItemId.PINK_PETALS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
