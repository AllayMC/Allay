package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCompoundStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCompoundStackInitializer {
  static void init() {
    ItemTypes.COMPOUND_TYPE = ItemTypeBuilder
            .builder(ItemCompoundStack.class)
            .vanillaItem(VanillaItemId.COMPOUND)
            .build();
  }
}
