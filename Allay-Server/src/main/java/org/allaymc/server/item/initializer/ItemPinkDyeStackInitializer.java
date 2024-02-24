package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPinkDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkDyeStackInitializer {
  static void init() {
    ItemTypes.PINK_DYE_TYPE = ItemTypeBuilder
            .builder(ItemPinkDyeStack.class)
            .vanillaItem(VanillaItemId.PINK_DYE)
            .build();
  }
}
