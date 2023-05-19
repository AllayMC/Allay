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
public interface ItemLodestoneCompass extends ItemStack {
    ItemType<ItemLodestoneCompass> TYPE = ItemTypeBuilder
            .builder(ItemLodestoneCompass.class)
            .vanillaItem(VanillaItemId.LODESTONE_COMPASS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
