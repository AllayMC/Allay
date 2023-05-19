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
public interface ItemCobbledDeepslateDoubleSlab extends ItemStack {
    ItemType<ItemCobbledDeepslateDoubleSlab> TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateDoubleSlab.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_DOUBLE_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
