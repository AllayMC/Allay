package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGrassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrassStackInitializer {
  static void init() {
    ItemTypes.GRASS_TYPE = ItemTypeBuilder
            .builder(ItemGrassStack.class)
            .vanillaItem(VanillaItemId.GRASS)
            .build();
  }
}
