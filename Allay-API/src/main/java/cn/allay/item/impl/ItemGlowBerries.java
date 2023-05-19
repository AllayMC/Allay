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
public interface ItemGlowBerries extends ItemStack {
    ItemType<ItemGlowBerries> TYPE = ItemTypeBuilder
            .builder(ItemGlowBerries.class)
            .vanillaItem(VanillaItemId.GLOW_BERRIES, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
