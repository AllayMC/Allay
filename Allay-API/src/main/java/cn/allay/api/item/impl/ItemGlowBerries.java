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
public interface ItemGlowBerries extends ItemStack {
    ItemType<ItemGlowBerries> TYPE = ItemTypeBuilder
            .builder(ItemGlowBerries.class)
            .vanillaItem(VanillaItemId.GLOW_BERRIES, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
