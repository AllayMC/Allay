package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemExposedCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemExposedCopperStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_COPPER)
            .build();
  }
}
