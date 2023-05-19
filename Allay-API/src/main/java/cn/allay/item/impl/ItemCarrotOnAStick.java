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
public interface ItemCarrotOnAStick extends ItemStack {
    ItemType<ItemCarrotOnAStick> TYPE = ItemTypeBuilder
            .builder(ItemCarrotOnAStick.class)
            .vanillaItem(VanillaItemId.CARROT_ON_A_STICK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
