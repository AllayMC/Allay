package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetheriteAxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteAxeStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_AXE_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteAxeStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_AXE)
            .build();
  }
}
