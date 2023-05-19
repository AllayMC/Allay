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
public interface ItemWarpedFungusOnAStick extends ItemStack {
    ItemType<ItemWarpedFungusOnAStick> TYPE = ItemTypeBuilder
            .builder(ItemWarpedFungusOnAStick.class)
            .vanillaItem(VanillaItemId.WARPED_FUNGUS_ON_A_STICK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
