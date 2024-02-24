package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFlowerBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowerBannerPatternStackInitializer {
  static void init() {
    ItemTypes.FLOWER_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemFlowerBannerPatternStack.class)
            .vanillaItem(VanillaItemId.FLOWER_BANNER_PATTERN)
            .build();
  }
}
