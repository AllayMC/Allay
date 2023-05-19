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
public interface ItemChainCommandBlock extends ItemStack {
    ItemType<ItemChainCommandBlock> TYPE = ItemTypeBuilder
            .builder(ItemChainCommandBlock.class)
            .vanillaItem(VanillaItemId.CHAIN_COMMAND_BLOCK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
