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
public interface ItemArmorStand extends ItemStack {
    ItemType<ItemArmorStand> TYPE = ItemTypeBuilder
            .builder(ItemArmorStand.class)
            .vanillaItem(VanillaItemId.ARMOR_STAND, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
