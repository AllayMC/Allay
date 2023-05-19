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
public interface ItemDeepslateCopperOre extends ItemStack {
    ItemType<ItemDeepslateCopperOre> TYPE = ItemTypeBuilder
            .builder(ItemDeepslateCopperOre.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_COPPER_ORE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
