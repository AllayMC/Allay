package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemOxidizedChiseledCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedChiseledCopperStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_CHISELED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedChiseledCopperStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_CHISELED_COPPER)
            .build();
  }
}
