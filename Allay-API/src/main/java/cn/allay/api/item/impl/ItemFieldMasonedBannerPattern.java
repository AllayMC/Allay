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
public interface ItemFieldMasonedBannerPattern extends ItemStack {
    ItemType<ItemFieldMasonedBannerPattern> TYPE = ItemTypeBuilder
            .builder(ItemFieldMasonedBannerPattern.class)
            .vanillaItem(VanillaItemId.FIELD_MASONED_BANNER_PATTERN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
