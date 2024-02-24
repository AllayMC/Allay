package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStandingBannerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStandingBannerStackInitializer {
  static void init() {
    ItemTypes.STANDING_BANNER_TYPE = ItemTypeBuilder
            .builder(ItemStandingBannerStack.class)
            .vanillaItem(VanillaItemId.STANDING_BANNER)
            .build();
  }
}
