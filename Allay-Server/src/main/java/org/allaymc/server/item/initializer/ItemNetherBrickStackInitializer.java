package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetherBrickStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherBrickStackInitializer {
  static void init() {
    ItemTypes.NETHER_BRICK_TYPE = ItemTypeBuilder
            .builder(ItemNetherBrickStack.class)
            .vanillaItem(VanillaItemId.NETHER_BRICK)
            .build();
  }
}
