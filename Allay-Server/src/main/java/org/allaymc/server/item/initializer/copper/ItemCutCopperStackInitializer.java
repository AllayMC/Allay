package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemCutCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCutCopperStackInitializer {
  static void init() {
    ItemTypes.CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemCutCopperStack.class)
            .vanillaItem(VanillaItemId.CUT_COPPER)
            .build();
  }
}
