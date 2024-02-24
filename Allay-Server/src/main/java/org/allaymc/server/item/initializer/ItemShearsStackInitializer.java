package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemShearsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShearsStackInitializer {
  static void init() {
    ItemTypes.SHEARS_TYPE = ItemTypeBuilder
            .builder(ItemShearsStack.class)
            .vanillaItem(VanillaItemId.SHEARS)
            .build();
  }
}
