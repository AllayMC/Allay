package cn.allay.api.item.interfaces.standingbanner;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStandingBannerStack extends ItemStack {
    ItemType<ItemStandingBannerStack> STANDING_BANNER_TYPE = ItemTypeBuilder
            .builder(ItemStandingBannerStack.class)
            .vanillaItem(VanillaItemId.STANDING_BANNER)
            .build();
}
