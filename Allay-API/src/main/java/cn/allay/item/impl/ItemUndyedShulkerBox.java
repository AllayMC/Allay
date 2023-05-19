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
public interface ItemUndyedShulkerBox extends ItemStack {
    ItemType<ItemUndyedShulkerBox> TYPE = ItemTypeBuilder
            .builder(ItemUndyedShulkerBox.class)
            .vanillaItem(VanillaItemId.UNDYED_SHULKER_BOX, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
