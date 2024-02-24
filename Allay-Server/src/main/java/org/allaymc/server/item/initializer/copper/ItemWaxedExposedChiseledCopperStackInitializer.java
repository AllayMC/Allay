package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedExposedChiseledCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedChiseledCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_EXPOSED_CHISELED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedChiseledCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_CHISELED_COPPER)
            .build();
  }
}
