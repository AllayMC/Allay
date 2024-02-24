package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRailStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRailStackInitializer {
  static void init() {
    ItemTypes.RAIL_TYPE = ItemTypeBuilder
            .builder(ItemRailStack.class)
            .vanillaItem(VanillaItemId.RAIL)
            .build();
  }
}
