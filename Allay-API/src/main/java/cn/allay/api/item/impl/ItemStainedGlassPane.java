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
public interface ItemStainedGlassPane extends ItemStack {
    ItemType<ItemStainedGlassPane> TYPE = ItemTypeBuilder
            .builder(ItemStainedGlassPane.class)
            .vanillaItem(VanillaItemId.STAINED_GLASS_PANE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
