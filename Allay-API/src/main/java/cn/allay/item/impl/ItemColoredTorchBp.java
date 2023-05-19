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
public interface ItemColoredTorchBp extends ItemStack {
    ItemType<ItemColoredTorchBp> TYPE = ItemTypeBuilder
            .builder(ItemColoredTorchBp.class)
            .vanillaItem(VanillaItemId.COLORED_TORCH_BP, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
