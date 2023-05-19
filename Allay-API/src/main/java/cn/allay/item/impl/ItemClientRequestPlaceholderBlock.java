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
public interface ItemClientRequestPlaceholderBlock extends ItemStack {
    ItemType<ItemClientRequestPlaceholderBlock> TYPE = ItemTypeBuilder
            .builder(ItemClientRequestPlaceholderBlock.class)
            .vanillaItem(VanillaItemId.CLIENT_REQUEST_PLACEHOLDER_BLOCK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
