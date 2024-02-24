package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWeatheredChiseledCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredChiseledCopperStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_CHISELED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredChiseledCopperStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_CHISELED_COPPER)
            .build();
  }
}
