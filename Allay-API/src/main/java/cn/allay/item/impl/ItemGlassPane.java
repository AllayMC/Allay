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
public interface ItemGlassPane extends ItemStack {
    ItemType<ItemGlassPane> TYPE = ItemTypeBuilder
            .builder(ItemGlassPane.class)
            .vanillaItem(VanillaItemId.GLASS_PANE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
