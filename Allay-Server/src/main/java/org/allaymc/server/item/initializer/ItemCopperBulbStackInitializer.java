package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCopperBulbStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperBulbStackInitializer {
  static void init() {
    ItemTypes.COPPER_BULB_TYPE = ItemTypeBuilder
            .builder(ItemCopperBulbStack.class)
            .vanillaItem(VanillaItemId.COPPER_BULB)
            .build();
  }
}
