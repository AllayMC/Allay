package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetherStarStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherStarStackInitializer {
  static void init() {
    ItemTypes.NETHER_STAR_TYPE = ItemTypeBuilder
            .builder(ItemNetherStarStack.class)
            .vanillaItem(VanillaItemId.NETHER_STAR)
            .build();
  }
}
