package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWhiteDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteDyeStackInitializer {
  static void init() {
    ItemTypes.WHITE_DYE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteDyeStack.class)
            .vanillaItem(VanillaItemId.WHITE_DYE)
            .build();
  }
}
