package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondAxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondAxeStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_AXE_TYPE = ItemTypeBuilder
            .builder(ItemDiamondAxeStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_AXE)
            .build();
  }
}
