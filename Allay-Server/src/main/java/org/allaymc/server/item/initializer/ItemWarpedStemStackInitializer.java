package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWarpedStemStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedStemStackInitializer {
  static void init() {
    ItemTypes.WARPED_STEM_TYPE = ItemTypeBuilder
            .builder(ItemWarpedStemStack.class)
            .vanillaItem(VanillaItemId.WARPED_STEM)
            .build();
  }
}
