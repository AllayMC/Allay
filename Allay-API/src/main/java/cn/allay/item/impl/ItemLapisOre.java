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
public interface ItemLapisOre extends ItemStack {
    ItemType<ItemLapisOre> TYPE = ItemTypeBuilder
            .builder(ItemLapisOre.class)
            .vanillaItem(VanillaItemId.LAPIS_ORE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
