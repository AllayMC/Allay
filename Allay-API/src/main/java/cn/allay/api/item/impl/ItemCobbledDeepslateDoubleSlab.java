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
public interface ItemCobbledDeepslateDoubleSlab extends ItemStack {
    ItemType<ItemCobbledDeepslateDoubleSlab> TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateDoubleSlab.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_DOUBLE_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
