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
public interface ItemBirchFenceGate extends ItemStack {
    ItemType<ItemBirchFenceGate> TYPE = ItemTypeBuilder
            .builder(ItemBirchFenceGate.class)
            .vanillaItem(VanillaItemId.BIRCH_FENCE_GATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
