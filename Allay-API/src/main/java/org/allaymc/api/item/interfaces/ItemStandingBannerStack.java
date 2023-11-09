package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
