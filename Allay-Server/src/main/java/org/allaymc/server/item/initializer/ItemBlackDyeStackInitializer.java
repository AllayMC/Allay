package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBlackDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackDyeStackInitializer {
  static void init() {
    ItemTypes.BLACK_DYE_TYPE = ItemTypeBuilder
            .builder(ItemBlackDyeStack.class)
            .vanillaItem(VanillaItemId.BLACK_DYE)
            .build();
  }
}
