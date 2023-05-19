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
public interface ItemSpruceTrapdoor extends ItemStack {
    ItemType<ItemSpruceTrapdoor> TYPE = ItemTypeBuilder
            .builder(ItemSpruceTrapdoor.class)
            .vanillaItem(VanillaItemId.SPRUCE_TRAPDOOR, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
