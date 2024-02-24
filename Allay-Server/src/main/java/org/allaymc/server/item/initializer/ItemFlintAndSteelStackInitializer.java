package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFlintAndSteelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlintAndSteelStackInitializer {
  static void init() {
    ItemTypes.FLINT_AND_STEEL_TYPE = ItemTypeBuilder
            .builder(ItemFlintAndSteelStack.class)
            .vanillaItem(VanillaItemId.FLINT_AND_STEEL)
            .build();
  }
}
