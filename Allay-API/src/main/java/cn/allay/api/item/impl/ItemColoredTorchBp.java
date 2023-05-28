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
public interface ItemColoredTorchBp extends ItemStack {
    ItemType<ItemColoredTorchBp> TYPE = ItemTypeBuilder
            .builder(ItemColoredTorchBp.class)
            .vanillaItem(VanillaItemId.COLORED_TORCH_BP, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
