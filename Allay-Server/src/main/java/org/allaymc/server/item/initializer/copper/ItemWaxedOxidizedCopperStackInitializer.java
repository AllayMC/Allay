package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedOxidizedCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_COPPER)
            .build();
  }
}
