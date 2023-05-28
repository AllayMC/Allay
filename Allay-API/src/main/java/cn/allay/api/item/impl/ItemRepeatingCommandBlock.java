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
public interface ItemRepeatingCommandBlock extends ItemStack {
    ItemType<ItemRepeatingCommandBlock> TYPE = ItemTypeBuilder
            .builder(ItemRepeatingCommandBlock.class)
            .vanillaItem(VanillaItemId.REPEATING_COMMAND_BLOCK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
