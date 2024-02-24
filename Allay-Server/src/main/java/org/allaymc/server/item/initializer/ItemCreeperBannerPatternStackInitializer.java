package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCreeperBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCreeperBannerPatternStackInitializer {
  static void init() {
    ItemTypes.CREEPER_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemCreeperBannerPatternStack.class)
            .vanillaItem(VanillaItemId.CREEPER_BANNER_PATTERN)
            .build();
  }
}
