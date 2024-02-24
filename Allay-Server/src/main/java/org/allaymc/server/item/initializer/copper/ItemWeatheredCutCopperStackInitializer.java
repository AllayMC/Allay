package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWeatheredCutCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCutCopperStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCutCopperStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER)
            .build();
  }
}
