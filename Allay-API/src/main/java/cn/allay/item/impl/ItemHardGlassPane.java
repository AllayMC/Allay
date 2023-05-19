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
public interface ItemHardGlassPane extends ItemStack {
    ItemType<ItemHardGlassPane> TYPE = ItemTypeBuilder
            .builder(ItemHardGlassPane.class)
            .vanillaItem(VanillaItemId.HARD_GLASS_PANE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
