package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBannerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBannerStackInitializer {
  static void init() {
    ItemTypes.BANNER_TYPE = ItemTypeBuilder
            .builder(ItemBannerStack.class)
            .vanillaItem(VanillaItemId.BANNER)
            .build();
  }
}
