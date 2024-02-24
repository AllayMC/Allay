package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCartographyTableStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCartographyTableStackInitializer {
  static void init() {
    ItemTypes.CARTOGRAPHY_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemCartographyTableStack.class)
            .vanillaItem(VanillaItemId.CARTOGRAPHY_TABLE)
            .build();
  }
}
