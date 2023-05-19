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
public interface ItemBed extends ItemStack {
    ItemType<ItemBed> TYPE = ItemTypeBuilder
            .builder(ItemBed.class)
            .vanillaItem(VanillaItemId.BED, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
