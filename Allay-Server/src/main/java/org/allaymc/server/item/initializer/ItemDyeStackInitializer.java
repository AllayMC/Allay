package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDyeStackInitializer {
  static void init() {
    ItemTypes.DYE_TYPE = ItemTypeBuilder
            .builder(ItemDyeStack.class)
            .vanillaItem(VanillaItemId.DYE)
            .build();
  }
}
