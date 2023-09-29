package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlobeBannerPatternStack extends ItemStack {
  ItemType<ItemGlobeBannerPatternStack> GLOBE_BANNER_PATTERN_TYPE = ItemTypeBuilder
          .builder(ItemGlobeBannerPatternStack.class)
          .vanillaItem(VanillaItemId.GLOBE_BANNER_PATTERN)
          .build();
}
