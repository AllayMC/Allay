package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenAxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenAxeStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_AXE_TYPE = ItemTypeBuilder
            .builder(ItemGoldenAxeStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_AXE)
            .build();
  }
}
