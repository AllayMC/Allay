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
public interface ItemBalloon extends ItemStack {
    ItemType<ItemBalloon> TYPE = ItemTypeBuilder
            .builder(ItemBalloon.class)
            .vanillaItem(VanillaItemId.BALLOON, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
