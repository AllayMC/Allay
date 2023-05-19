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
public interface ItemStick extends ItemStack {
    ItemType<ItemStick> TYPE = ItemTypeBuilder
            .builder(ItemStick.class)
            .vanillaItem(VanillaItemId.STICK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
