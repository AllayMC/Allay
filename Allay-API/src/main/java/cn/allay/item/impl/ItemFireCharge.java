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
public interface ItemFireCharge extends ItemStack {
    ItemType<ItemFireCharge> TYPE = ItemTypeBuilder
            .builder(ItemFireCharge.class)
            .vanillaItem(VanillaItemId.FIRE_CHARGE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
