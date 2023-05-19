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
public interface ItemCrimsonButton extends ItemStack {
    ItemType<ItemCrimsonButton> TYPE = ItemTypeBuilder
            .builder(ItemCrimsonButton.class)
            .vanillaItem(VanillaItemId.CRIMSON_BUTTON, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
