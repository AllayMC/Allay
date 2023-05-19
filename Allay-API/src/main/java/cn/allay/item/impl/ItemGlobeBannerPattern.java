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
public interface ItemGlobeBannerPattern extends ItemStack {
    ItemType<ItemGlobeBannerPattern> TYPE = ItemTypeBuilder
            .builder(ItemGlobeBannerPattern.class)
            .vanillaItem(VanillaItemId.GLOBE_BANNER_PATTERN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
