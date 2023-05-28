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
public interface ItemTorchflowerCrop extends ItemStack {
    ItemType<ItemTorchflowerCrop> TYPE = ItemTypeBuilder
            .builder(ItemTorchflowerCrop.class)
            .vanillaItem(VanillaItemId.TORCHFLOWER_CROP, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
