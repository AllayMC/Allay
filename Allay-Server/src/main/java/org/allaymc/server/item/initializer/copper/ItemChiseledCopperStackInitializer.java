package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemChiseledCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledCopperStackInitializer {
  static void init() {
    ItemTypes.CHISELED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemChiseledCopperStack.class)
            .vanillaItem(VanillaItemId.CHISELED_COPPER)
            .build();
  }
}
