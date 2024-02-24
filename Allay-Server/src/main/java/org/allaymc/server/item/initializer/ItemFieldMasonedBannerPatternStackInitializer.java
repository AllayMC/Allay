package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFieldMasonedBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFieldMasonedBannerPatternStackInitializer {
  static void init() {
    ItemTypes.FIELD_MASONED_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemFieldMasonedBannerPatternStack.class)
            .vanillaItem(VanillaItemId.FIELD_MASONED_BANNER_PATTERN)
            .build();
  }
}
