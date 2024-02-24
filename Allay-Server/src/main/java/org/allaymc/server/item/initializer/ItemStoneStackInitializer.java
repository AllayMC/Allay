package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneStackInitializer {
  static void init() {
    ItemTypes.STONE_TYPE = ItemTypeBuilder
            .builder(ItemStoneStack.class)
            .vanillaItem(VanillaItemId.STONE)
            .build();
  }
}
