package cn.allay.api.item.interfaces.piglinbannerpattern;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPiglinBannerPatternStack extends ItemStack {
    ItemType<ItemPiglinBannerPatternStack> PIGLIN_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemPiglinBannerPatternStack.class)
            .vanillaItem(VanillaItemId.PIGLIN_BANNER_PATTERN)
            .build();
}
