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
public interface ItemOakHangingSign extends ItemStack {
    ItemType<ItemOakHangingSign> TYPE = ItemTypeBuilder
            .builder(ItemOakHangingSign.class)
            .vanillaItem(VanillaItemId.OAK_HANGING_SIGN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
