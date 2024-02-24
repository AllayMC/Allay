package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSmithingTableStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmithingTableStackInitializer {
  static void init() {
    ItemTypes.SMITHING_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemSmithingTableStack.class)
            .vanillaItem(VanillaItemId.SMITHING_TABLE)
            .build();
  }
}
