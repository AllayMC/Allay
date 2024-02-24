package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSmoothStoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothStoneStackInitializer {
  static void init() {
    ItemTypes.SMOOTH_STONE_TYPE = ItemTypeBuilder
            .builder(ItemSmoothStoneStack.class)
            .vanillaItem(VanillaItemId.SMOOTH_STONE)
            .build();
  }
}
