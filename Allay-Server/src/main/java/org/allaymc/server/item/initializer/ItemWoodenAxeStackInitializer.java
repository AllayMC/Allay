package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWoodenAxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenAxeStackInitializer {
  static void init() {
    ItemTypes.WOODEN_AXE_TYPE = ItemTypeBuilder
            .builder(ItemWoodenAxeStack.class)
            .vanillaItem(VanillaItemId.WOODEN_AXE)
            .build();
  }
}
