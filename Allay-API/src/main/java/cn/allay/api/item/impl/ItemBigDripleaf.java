package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

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
