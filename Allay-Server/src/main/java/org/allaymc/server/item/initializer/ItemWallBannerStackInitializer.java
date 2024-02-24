package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWallBannerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWallBannerStackInitializer {
  static void init() {
    ItemTypes.WALL_BANNER_TYPE = ItemTypeBuilder
            .builder(ItemWallBannerStack.class)
            .vanillaItem(VanillaItemId.WALL_BANNER)
            .build();
  }
}
