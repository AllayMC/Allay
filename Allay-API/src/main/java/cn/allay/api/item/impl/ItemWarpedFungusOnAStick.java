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
public interface ItemWarpedFungusOnAStick extends ItemStack {
    ItemType<ItemWarpedFungusOnAStick> TYPE = ItemTypeBuilder
            .builder(ItemWarpedFungusOnAStick.class)
            .vanillaItem(VanillaItemId.WARPED_FUNGUS_ON_A_STICK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
