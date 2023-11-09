package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
