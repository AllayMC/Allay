package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemExposedCutCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCutCopperStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemExposedCutCopperStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_CUT_COPPER)
            .build();
  }
}
