package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBrickStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrickStackInitializer {
  static void init() {
    ItemTypes.BRICK_TYPE = ItemTypeBuilder
            .builder(ItemBrickStack.class)
            .vanillaItem(VanillaItemId.BRICK)
            .build();
  }
}
