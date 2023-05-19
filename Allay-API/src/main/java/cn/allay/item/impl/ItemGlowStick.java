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
public interface ItemGlowStick extends ItemStack {
    ItemType<ItemGlowStick> TYPE = ItemTypeBuilder
            .builder(ItemGlowStick.class)
            .vanillaItem(VanillaItemId.GLOW_STICK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
