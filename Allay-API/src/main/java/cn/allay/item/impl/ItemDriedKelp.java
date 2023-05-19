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
public interface ItemDriedKelp extends ItemStack {
    ItemType<ItemDriedKelp> TYPE = ItemTypeBuilder
            .builder(ItemDriedKelp.class)
            .vanillaItem(VanillaItemId.DRIED_KELP, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
