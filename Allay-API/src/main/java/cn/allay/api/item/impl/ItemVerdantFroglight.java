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
public interface ItemVerdantFroglight extends ItemStack {
    ItemType<ItemVerdantFroglight> TYPE = ItemTypeBuilder
            .builder(ItemVerdantFroglight.class)
            .vanillaItem(VanillaItemId.VERDANT_FROGLIGHT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
