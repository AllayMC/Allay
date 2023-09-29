package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBordureIndentedBannerPatternStack extends ItemStack {
  ItemType<ItemBordureIndentedBannerPatternStack> BORDURE_INDENTED_BANNER_PATTERN_TYPE = ItemTypeBuilder
          .builder(ItemBordureIndentedBannerPatternStack.class)
          .vanillaItem(VanillaItemId.BORDURE_INDENTED_BANNER_PATTERN)
          .build();
}
