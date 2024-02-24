package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCyanDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanDyeStackInitializer {
  static void init() {
    ItemTypes.CYAN_DYE_TYPE = ItemTypeBuilder
            .builder(ItemCyanDyeStack.class)
            .vanillaItem(VanillaItemId.CYAN_DYE)
            .build();
  }
}
