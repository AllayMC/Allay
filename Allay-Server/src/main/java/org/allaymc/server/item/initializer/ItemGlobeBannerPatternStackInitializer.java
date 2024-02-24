package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlobeBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlobeBannerPatternStackInitializer {
  static void init() {
    ItemTypes.GLOBE_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemGlobeBannerPatternStack.class)
            .vanillaItem(VanillaItemId.GLOBE_BANNER_PATTERN)
            .build();
  }
}
