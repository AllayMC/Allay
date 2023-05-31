package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBread extends ItemStack {
    ItemType<ItemBread> TYPE = ItemTypeBuilder
            .builder(ItemBread.class)
            .vanillaItem(VanillaItemId.BREAD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
