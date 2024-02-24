package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMossyCobblestoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossyCobblestoneStackInitializer {
  static void init() {
    ItemTypes.MOSSY_COBBLESTONE_TYPE = ItemTypeBuilder
            .builder(ItemMossyCobblestoneStack.class)
            .vanillaItem(VanillaItemId.MOSSY_COBBLESTONE)
            .build();
  }
}
