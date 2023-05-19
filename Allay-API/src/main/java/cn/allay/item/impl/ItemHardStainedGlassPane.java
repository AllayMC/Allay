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
public interface ItemHardStainedGlassPane extends ItemStack {
    ItemType<ItemHardStainedGlassPane> TYPE = ItemTypeBuilder
            .builder(ItemHardStainedGlassPane.class)
            .vanillaItem(VanillaItemId.HARD_STAINED_GLASS_PANE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
