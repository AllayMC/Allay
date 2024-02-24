package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedOxidizedChiseledCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedChiseledCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_CHISELED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedChiseledCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CHISELED_COPPER)
            .build();
  }
}
