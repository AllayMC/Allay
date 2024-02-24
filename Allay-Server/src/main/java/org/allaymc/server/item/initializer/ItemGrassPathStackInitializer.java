package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGrassPathStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrassPathStackInitializer {
  static void init() {
    ItemTypes.GRASS_PATH_TYPE = ItemTypeBuilder
            .builder(ItemGrassPathStack.class)
            .vanillaItem(VanillaItemId.GRASS_PATH)
            .build();
  }
}
