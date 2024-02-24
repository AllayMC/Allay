package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSkullBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkullBannerPatternStackInitializer {
  static void init() {
    ItemTypes.SKULL_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemSkullBannerPatternStack.class)
            .vanillaItem(VanillaItemId.SKULL_BANNER_PATTERN)
            .build();
  }
}
