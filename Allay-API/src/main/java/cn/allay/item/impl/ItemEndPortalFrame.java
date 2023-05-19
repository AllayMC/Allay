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
public interface ItemEndPortalFrame extends ItemStack {
    ItemType<ItemEndPortalFrame> TYPE = ItemTypeBuilder
            .builder(ItemEndPortalFrame.class)
            .vanillaItem(VanillaItemId.END_PORTAL_FRAME, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
