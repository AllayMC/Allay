package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWeatheredCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCopperStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCopperStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_COPPER)
            .build();
  }
}
