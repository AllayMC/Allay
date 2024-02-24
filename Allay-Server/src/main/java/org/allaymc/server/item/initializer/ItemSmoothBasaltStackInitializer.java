package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSmoothBasaltStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothBasaltStackInitializer {
  static void init() {
    ItemTypes.SMOOTH_BASALT_TYPE = ItemTypeBuilder
            .builder(ItemSmoothBasaltStack.class)
            .vanillaItem(VanillaItemId.SMOOTH_BASALT)
            .build();
  }
}
