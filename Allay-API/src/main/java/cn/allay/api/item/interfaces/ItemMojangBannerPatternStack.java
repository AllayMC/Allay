package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMojangBannerPatternStack extends ItemStack {
    ItemType<ItemMojangBannerPatternStack> MOJANG_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemMojangBannerPatternStack.class)
            .vanillaItem(VanillaItemId.MOJANG_BANNER_PATTERN)
            .build();
}
