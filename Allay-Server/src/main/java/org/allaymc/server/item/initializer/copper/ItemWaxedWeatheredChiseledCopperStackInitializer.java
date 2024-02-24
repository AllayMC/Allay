package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedWeatheredChiseledCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredChiseledCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_WEATHERED_CHISELED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredChiseledCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_CHISELED_COPPER)
            .build();
  }
}
