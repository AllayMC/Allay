package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetherSproutsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherSproutsStackInitializer {
  static void init() {
    ItemTypes.NETHER_SPROUTS_TYPE = ItemTypeBuilder
            .builder(ItemNetherSproutsStack.class)
            .vanillaItem(VanillaItemId.NETHER_SPROUTS)
            .build();
  }
}
