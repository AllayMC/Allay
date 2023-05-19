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
public interface ItemWanderingTraderSpawnEgg extends ItemStack {
    ItemType<ItemWanderingTraderSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemWanderingTraderSpawnEgg.class)
            .vanillaItem(VanillaItemId.WANDERING_TRADER_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
