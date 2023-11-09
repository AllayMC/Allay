package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
