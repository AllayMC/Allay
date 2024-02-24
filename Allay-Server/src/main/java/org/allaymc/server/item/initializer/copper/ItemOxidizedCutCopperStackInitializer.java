package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemOxidizedCutCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCutCopperStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCutCopperStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_CUT_COPPER)
            .build();
  }
}
