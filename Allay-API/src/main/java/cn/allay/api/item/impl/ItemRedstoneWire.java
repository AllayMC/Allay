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
public interface ItemRedstoneWire extends ItemStack {
    ItemType<ItemRedstoneWire> TYPE = ItemTypeBuilder
            .builder(ItemRedstoneWire.class)
            .vanillaItem(VanillaItemId.REDSTONE_WIRE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
