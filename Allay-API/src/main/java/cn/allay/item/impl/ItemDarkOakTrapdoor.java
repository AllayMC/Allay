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
public interface ItemDarkOakTrapdoor extends ItemStack {
    ItemType<ItemDarkOakTrapdoor> TYPE = ItemTypeBuilder
            .builder(ItemDarkOakTrapdoor.class)
            .vanillaItem(VanillaItemId.DARK_OAK_TRAPDOOR, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
