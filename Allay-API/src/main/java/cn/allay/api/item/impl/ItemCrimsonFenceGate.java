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
public interface ItemCrimsonFenceGate extends ItemStack {
    ItemType<ItemCrimsonFenceGate> TYPE = ItemTypeBuilder
            .builder(ItemCrimsonFenceGate.class)
            .vanillaItem(VanillaItemId.CRIMSON_FENCE_GATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
