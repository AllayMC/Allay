package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedDyeStackInitializer {
  static void init() {
    ItemTypes.RED_DYE_TYPE = ItemTypeBuilder
            .builder(ItemRedDyeStack.class)
            .vanillaItem(VanillaItemId.RED_DYE)
            .build();
  }
}
