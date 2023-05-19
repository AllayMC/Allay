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
public interface ItemElement7 extends ItemStack {
    ItemType<ItemElement7> TYPE = ItemTypeBuilder
            .builder(ItemElement7.class)
            .vanillaItem(VanillaItemId.ELEMENT_7, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
