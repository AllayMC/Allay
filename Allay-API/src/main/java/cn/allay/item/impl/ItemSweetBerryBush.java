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
public interface ItemSweetBerryBush extends ItemStack {
    ItemType<ItemSweetBerryBush> TYPE = ItemTypeBuilder
            .builder(ItemSweetBerryBush.class)
            .vanillaItem(VanillaItemId.SWEET_BERRY_BUSH, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
