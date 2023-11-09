package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCreeperBannerPatternStack extends ItemStack {
  ItemType<ItemCreeperBannerPatternStack> CREEPER_BANNER_PATTERN_TYPE = ItemTypeBuilder
          .builder(ItemCreeperBannerPatternStack.class)
          .vanillaItem(VanillaItemId.CREEPER_BANNER_PATTERN)
          .build();
}
