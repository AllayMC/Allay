package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronAxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronAxeStackInitializer {
  static void init() {
    ItemTypes.IRON_AXE_TYPE = ItemTypeBuilder
            .builder(ItemIronAxeStack.class)
            .vanillaItem(VanillaItemId.IRON_AXE)
            .build();
  }
}
