package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_COPPER)
            .build();
  }
}
