package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBordureIndentedBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBordureIndentedBannerPatternStackInitializer {
  static void init() {
    ItemTypes.BORDURE_INDENTED_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemBordureIndentedBannerPatternStack.class)
            .vanillaItem(VanillaItemId.BORDURE_INDENTED_BANNER_PATTERN)
            .build();
  }
}
