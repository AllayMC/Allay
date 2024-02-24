package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEnchantingTableStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnchantingTableStackInitializer {
  static void init() {
    ItemTypes.ENCHANTING_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemEnchantingTableStack.class)
            .vanillaItem(VanillaItemId.ENCHANTING_TABLE)
            .build();
  }
}
