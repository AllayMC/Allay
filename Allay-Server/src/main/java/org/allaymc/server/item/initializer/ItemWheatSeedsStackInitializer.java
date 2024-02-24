package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWheatSeedsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWheatSeedsStackInitializer {
  static void init() {
    ItemTypes.WHEAT_SEEDS_TYPE = ItemTypeBuilder
            .builder(ItemWheatSeedsStack.class)
            .vanillaItem(VanillaItemId.WHEAT_SEEDS)
            .build();
  }
}
