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
public interface ItemStrippedOakLog extends ItemStack {
    ItemType<ItemStrippedOakLog> TYPE = ItemTypeBuilder
            .builder(ItemStrippedOakLog.class)
            .vanillaItem(VanillaItemId.STRIPPED_OAK_LOG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
