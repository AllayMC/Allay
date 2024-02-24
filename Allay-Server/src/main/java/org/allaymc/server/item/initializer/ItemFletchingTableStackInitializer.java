package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFletchingTableStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFletchingTableStackInitializer {
  static void init() {
    ItemTypes.FLETCHING_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemFletchingTableStack.class)
            .vanillaItem(VanillaItemId.FLETCHING_TABLE)
            .build();
  }
}
