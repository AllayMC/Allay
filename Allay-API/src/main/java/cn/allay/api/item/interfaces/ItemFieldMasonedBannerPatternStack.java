package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFieldMasonedBannerPatternStack extends ItemStack {
    ItemType<ItemFieldMasonedBannerPatternStack> FIELD_MASONED_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemFieldMasonedBannerPatternStack.class)
            .vanillaItem(VanillaItemId.FIELD_MASONED_BANNER_PATTERN)
            .build();
}
