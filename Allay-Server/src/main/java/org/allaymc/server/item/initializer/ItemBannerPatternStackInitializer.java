package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBannerPatternStackInitializer {
  static void init() {
    ItemTypes.BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemBannerPatternStack.class)
            .vanillaItem(VanillaItemId.BANNER_PATTERN)
            .build();
  }
}
