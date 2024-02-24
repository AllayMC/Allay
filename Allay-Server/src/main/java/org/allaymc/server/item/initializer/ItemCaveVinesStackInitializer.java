package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCaveVinesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveVinesStackInitializer {
  static void init() {
    ItemTypes.CAVE_VINES_TYPE = ItemTypeBuilder
            .builder(ItemCaveVinesStack.class)
            .vanillaItem(VanillaItemId.CAVE_VINES)
            .build();
  }
}
