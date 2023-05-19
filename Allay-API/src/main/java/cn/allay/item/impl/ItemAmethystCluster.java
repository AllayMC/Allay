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
public interface ItemAmethystCluster extends ItemStack {
    ItemType<ItemAmethystCluster> TYPE = ItemTypeBuilder
            .builder(ItemAmethystCluster.class)
            .vanillaItem(VanillaItemId.AMETHYST_CLUSTER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
