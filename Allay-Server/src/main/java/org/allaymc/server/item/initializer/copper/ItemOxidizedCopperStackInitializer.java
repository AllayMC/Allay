package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemOxidizedCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCopperStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCopperStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_COPPER)
            .build();
  }
}
