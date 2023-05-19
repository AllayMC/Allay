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
public interface ItemGlowInkSac extends ItemStack {
    ItemType<ItemGlowInkSac> TYPE = ItemTypeBuilder
            .builder(ItemGlowInkSac.class)
            .vanillaItem(VanillaItemId.GLOW_INK_SAC, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
