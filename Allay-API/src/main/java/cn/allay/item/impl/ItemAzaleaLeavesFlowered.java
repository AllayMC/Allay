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
public interface ItemAzaleaLeavesFlowered extends ItemStack {
    ItemType<ItemAzaleaLeavesFlowered> TYPE = ItemTypeBuilder
            .builder(ItemAzaleaLeavesFlowered.class)
            .vanillaItem(VanillaItemId.AZALEA_LEAVES_FLOWERED, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
