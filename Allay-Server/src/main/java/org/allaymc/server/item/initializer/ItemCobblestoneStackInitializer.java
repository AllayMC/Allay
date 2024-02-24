package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCobblestoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobblestoneStackInitializer {
  static void init() {
    ItemTypes.COBBLESTONE_TYPE = ItemTypeBuilder
            .builder(ItemCobblestoneStack.class)
            .vanillaItem(VanillaItemId.COBBLESTONE)
            .build();
  }
}
