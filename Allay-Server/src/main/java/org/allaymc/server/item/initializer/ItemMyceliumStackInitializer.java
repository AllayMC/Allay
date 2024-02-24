package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMyceliumStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMyceliumStackInitializer {
  static void init() {
    ItemTypes.MYCELIUM_TYPE = ItemTypeBuilder
            .builder(ItemMyceliumStack.class)
            .vanillaItem(VanillaItemId.MYCELIUM)
            .build();
  }
}
