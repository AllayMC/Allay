package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedCutCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCutCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCutCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_CUT_COPPER)
            .build();
  }
}
