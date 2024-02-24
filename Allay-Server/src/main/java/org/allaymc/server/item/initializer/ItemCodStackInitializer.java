package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCodStackInitializer {
  static void init() {
    ItemTypes.COD_TYPE = ItemTypeBuilder
            .builder(ItemCodStack.class)
            .vanillaItem(VanillaItemId.COD)
            .build();
  }
}
