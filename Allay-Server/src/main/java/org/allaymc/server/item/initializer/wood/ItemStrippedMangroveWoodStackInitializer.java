package org.allaymc.server.item.initializer.wood;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wood.ItemStrippedMangroveWoodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedMangroveWoodStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_MANGROVE_WOOD_TYPE = ItemTypeBuilder
            .builder(ItemStrippedMangroveWoodStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_MANGROVE_WOOD)
            .build();
  }
}
