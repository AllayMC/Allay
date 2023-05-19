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
public interface ItemBigDripleaf extends ItemStack {
    ItemType<ItemBigDripleaf> TYPE = ItemTypeBuilder
            .builder(ItemBigDripleaf.class)
            .vanillaItem(VanillaItemId.BIG_DRIPLEAF, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
