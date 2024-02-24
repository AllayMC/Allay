package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemExposedChiseledCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedChiseledCopperStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_CHISELED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemExposedChiseledCopperStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_CHISELED_COPPER)
            .build();
  }
}
