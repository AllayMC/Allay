package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStrippedWarpedStemStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedWarpedStemStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_WARPED_STEM_TYPE = ItemTypeBuilder
            .builder(ItemStrippedWarpedStemStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_WARPED_STEM)
            .build();
  }
}
