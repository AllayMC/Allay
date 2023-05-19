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
public interface ItemStoneButton extends ItemStack {
    ItemType<ItemStoneButton> TYPE = ItemTypeBuilder
            .builder(ItemStoneButton.class)
            .vanillaItem(VanillaItemId.STONE_BUTTON, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
