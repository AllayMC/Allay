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
public interface ItemFlintAndSteel extends ItemStack {
    ItemType<ItemFlintAndSteel> TYPE = ItemTypeBuilder
            .builder(ItemFlintAndSteel.class)
            .vanillaItem(VanillaItemId.FLINT_AND_STEEL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
