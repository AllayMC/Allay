package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBannerPatternStack extends ItemStack {
    ItemType<ItemBannerPatternStack> BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemBannerPatternStack.class)
            .vanillaItem(VanillaItemId.BANNER_PATTERN)
            .build();
}
