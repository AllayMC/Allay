package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEndStoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndStoneStackInitializer {
  static void init() {
    ItemTypes.END_STONE_TYPE = ItemTypeBuilder
            .builder(ItemEndStoneStack.class)
            .vanillaItem(VanillaItemId.END_STONE)
            .build();
  }
}
