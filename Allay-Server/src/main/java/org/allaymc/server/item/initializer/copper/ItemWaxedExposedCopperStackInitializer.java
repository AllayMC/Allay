package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedExposedCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_EXPOSED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER)
            .build();
  }
}
