package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedWeatheredCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_WEATHERED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER)
            .build();
  }
}
