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
public interface ItemChainmailLeggings extends ItemStack {
    ItemType<ItemChainmailLeggings> TYPE = ItemTypeBuilder
            .builder(ItemChainmailLeggings.class)
            .vanillaItem(VanillaItemId.CHAINMAIL_LEGGINGS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
