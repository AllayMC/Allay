package org.allaymc.server.item.initializer.wood;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wood.ItemMangroveWoodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveWoodStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_WOOD_TYPE = ItemTypeBuilder
            .builder(ItemMangroveWoodStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_WOOD)
            .build();
  }
}
