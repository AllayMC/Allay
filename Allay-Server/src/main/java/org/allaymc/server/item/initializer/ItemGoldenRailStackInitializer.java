package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenRailStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenRailStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_RAIL_TYPE = ItemTypeBuilder
            .builder(ItemGoldenRailStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_RAIL)
            .build();
  }
}
