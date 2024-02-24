package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFeatherStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFeatherStackInitializer {
  static void init() {
    ItemTypes.FEATHER_TYPE = ItemTypeBuilder
            .builder(ItemFeatherStack.class)
            .vanillaItem(VanillaItemId.FEATHER)
            .build();
  }
}
