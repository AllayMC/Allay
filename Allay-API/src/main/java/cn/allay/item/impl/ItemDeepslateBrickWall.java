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
public interface ItemDeepslateBrickWall extends ItemStack {
    ItemType<ItemDeepslateBrickWall> TYPE = ItemTypeBuilder
            .builder(ItemDeepslateBrickWall.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_WALL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
