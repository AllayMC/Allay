package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedAcaciaLog extends ItemStack {
    ItemType<ItemStrippedAcaciaLog> TYPE = ItemTypeBuilder
            .builder(ItemStrippedAcaciaLog.class)
            .vanillaItem(VanillaItemId.STRIPPED_ACACIA_LOG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
