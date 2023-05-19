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
public interface ItemShelterPotteryShard extends ItemStack {
    ItemType<ItemShelterPotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemShelterPotteryShard.class)
            .vanillaItem(VanillaItemId.SHELTER_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
