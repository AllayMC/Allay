package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBlueIceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueIceStackInitializer {
  static void init() {
    ItemTypes.BLUE_ICE_TYPE = ItemTypeBuilder
            .builder(ItemBlueIceStack.class)
            .vanillaItem(VanillaItemId.BLUE_ICE)
            .build();
  }
}
