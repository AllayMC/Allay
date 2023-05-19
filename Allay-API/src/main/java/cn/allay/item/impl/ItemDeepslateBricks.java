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
public interface ItemDeepslateBricks extends ItemStack {
    ItemType<ItemDeepslateBricks> TYPE = ItemTypeBuilder
            .builder(ItemDeepslateBricks.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_BRICKS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
