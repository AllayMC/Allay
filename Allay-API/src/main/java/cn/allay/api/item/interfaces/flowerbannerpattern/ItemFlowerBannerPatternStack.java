package cn.allay.api.item.interfaces.flowerbannerpattern;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowerBannerPatternStack extends ItemStack {
    ItemType<ItemFlowerBannerPatternStack> FLOWER_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemFlowerBannerPatternStack.class)
            .vanillaItem(VanillaItemId.FLOWER_BANNER_PATTERN)
            .build();
}
