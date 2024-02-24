package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemNetherSproutsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemNetherSproutsStackInitializer {
  static void init() {
    ItemTypes.ITEM_NETHER_SPROUTS_TYPE = ItemTypeBuilder
            .builder(ItemItemNetherSproutsStack.class)
            .vanillaItem(VanillaItemId.ITEM_NETHER_SPROUTS)
            .build();
  }
}
