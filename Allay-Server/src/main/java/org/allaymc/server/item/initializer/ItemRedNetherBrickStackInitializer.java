package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedNetherBrickStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedNetherBrickStackInitializer {
  static void init() {
    ItemTypes.RED_NETHER_BRICK_TYPE = ItemTypeBuilder
            .builder(ItemRedNetherBrickStack.class)
            .vanillaItem(VanillaItemId.RED_NETHER_BRICK)
            .build();
  }
}
