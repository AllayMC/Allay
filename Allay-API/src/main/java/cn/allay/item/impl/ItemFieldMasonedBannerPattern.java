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
public interface ItemFieldMasonedBannerPattern extends ItemStack {
    ItemType<ItemFieldMasonedBannerPattern> TYPE = ItemTypeBuilder
            .builder(ItemFieldMasonedBannerPattern.class)
            .vanillaItem(VanillaItemId.FIELD_MASONED_BANNER_PATTERN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
