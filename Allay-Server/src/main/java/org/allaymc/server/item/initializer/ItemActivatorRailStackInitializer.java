package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemActivatorRailStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemActivatorRailStackInitializer {
  static void init() {
    ItemTypes.ACTIVATOR_RAIL_TYPE = ItemTypeBuilder
            .builder(ItemActivatorRailStack.class)
            .vanillaItem(VanillaItemId.ACTIVATOR_RAIL)
            .build();
  }
}
