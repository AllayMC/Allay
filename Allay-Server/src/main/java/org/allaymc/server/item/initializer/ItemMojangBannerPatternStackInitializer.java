package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMojangBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMojangBannerPatternStackInitializer {
  static void init() {
    ItemTypes.MOJANG_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemMojangBannerPatternStack.class)
            .vanillaItem(VanillaItemId.MOJANG_BANNER_PATTERN)
            .build();
  }
}
