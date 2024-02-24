package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemRawCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawCopperStackInitializer {
  static void init() {
    ItemTypes.RAW_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemRawCopperStack.class)
            .vanillaItem(VanillaItemId.RAW_COPPER)
            .build();
  }
}
