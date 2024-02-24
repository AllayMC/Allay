package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedChiseledCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedChiseledCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_CHISELED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedChiseledCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_CHISELED_COPPER)
            .build();
  }
}
