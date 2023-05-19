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
public interface ItemDragonEgg extends ItemStack {
    ItemType<ItemDragonEgg> TYPE = ItemTypeBuilder
            .builder(ItemDragonEgg.class)
            .vanillaItem(VanillaItemId.DRAGON_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
