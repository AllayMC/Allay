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
public interface ItemBone extends ItemStack {
    ItemType<ItemBone> TYPE = ItemTypeBuilder
            .builder(ItemBone.class)
            .vanillaItem(VanillaItemId.BONE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
